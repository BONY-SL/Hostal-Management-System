package com.hostalmanagement.Web.Application.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostalmanagement.Web.Application.dto.AuthenticationRequest;
import com.hostalmanagement.Web.Application.dto.AuthenticationResponse;
import com.hostalmanagement.Web.Application.dto.RegistrationRequest;
import com.hostalmanagement.Web.Application.model.StudentMailStore;
import com.hostalmanagement.Web.Application.model.Token;
import com.hostalmanagement.Web.Application.model.User;
import com.hostalmanagement.Web.Application.repository.StudentMailRepository;
import com.hostalmanagement.Web.Application.repository.TokenRepository;
import com.hostalmanagement.Web.Application.repository.UserRepository;
import com.hostalmanagement.Web.Application.util.Role;
import com.hostalmanagement.Web.Application.util.TokenType;
import com.hostalmanagement.Web.Application.webconfig.JwtService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    private final StudentMailRepository studentMailRepository;


    @Transactional
    public AuthenticationResponse register(RegistrationRequest registrationRequest) {


        Optional<StudentMailStore> studentMailStore = studentMailRepository.findByEmail(registrationRequest.getEmail());

        if(studentMailStore.isPresent()){
            Optional<User> exsistUser = userRepository.findByEmail(registrationRequest.getEmail());

            if(exsistUser.isPresent()){
                System.out.println("User Already Exsist");
                return null;
            }

            var user = User.builder()
                    .firstname(registrationRequest.getFirstname())
                    .lastname(registrationRequest.getLastname())
                    .email(registrationRequest.getEmail())
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .role(registrationRequest.getRole())
                    .build();

            var savedUser = userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);

            var refreshToken = jwtService.generateRefreshToken(user);

            saveUserToken(savedUser, jwtToken);



            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }else {
            System.out.println("Your Email is Not Registered In The System Yet");
            return null;
        }
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .userRole(user.getRole())
                .userId(user.getId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        final String refreshToken;

        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Transactional
    protected void revokeAllUserTokens(User user) {

        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


    @Transactional
    public void saveUserToken(User user, String jwtToken) {


        Optional<Token> existingToken = tokenRepository.findByToken(jwtToken);

        Token token;
        if (existingToken.isPresent()) {

            token = existingToken.get();
            token.setUser(user);
            token.setExpired(false);
            token.setRevoked(false);
        } else {

            token = Token.builder()
                    .user(user)
                    .token(jwtToken)
                    .tokenType(TokenType.BEARER)
                    .expired(false)
                    .revoked(false)
                    .build();
        }
        tokenRepository.save(token);

    }


    @PostConstruct
    public void setStudentMails(){

        List<StudentMailStore> studentMailStores = studentMailRepository.findAll();


        if(studentMailStores.isEmpty()){

            for (int i = 100 ; i <= 110 ; i++){

                StudentMailStore studentMailStore = StudentMailStore.builder()
                        .email("studentExample"+i+"@ruhuna.ac.lk")
                        .build();

                studentMailRepository.save(studentMailStore);
            }
        }

        Optional<User> exsistUser = userRepository.findByEmail("admin@gmail.com");

        if(exsistUser.isPresent()){
            System.out.println("User Already Exsist");
            return;
        }

        var user = User.builder()
                .firstname("dilshan")
                .lastname("danidu")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin12345"))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);

    }
}

