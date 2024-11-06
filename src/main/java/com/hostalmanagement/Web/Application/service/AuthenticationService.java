package com.hostalmanagement.Web.Application.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostalmanagement.Web.Application.dto.AuthenticationRequest;
import com.hostalmanagement.Web.Application.dto.AuthenticationResponse;
import com.hostalmanagement.Web.Application.dto.RegistrationRequest;
import com.hostalmanagement.Web.Application.model.SaveActivatedCoeds;
import com.hostalmanagement.Web.Application.model.StudentMailStore;
import com.hostalmanagement.Web.Application.model.Token;
import com.hostalmanagement.Web.Application.model.User;
import com.hostalmanagement.Web.Application.repository.SaveCodeRepo;
import com.hostalmanagement.Web.Application.repository.StudentMailRepository;
import com.hostalmanagement.Web.Application.repository.TokenRepository;
import com.hostalmanagement.Web.Application.repository.UserRepository;
import com.hostalmanagement.Web.Application.util.EmailService;
import com.hostalmanagement.Web.Application.util.EmailTemplateName;
import com.hostalmanagement.Web.Application.util.Role;
import com.hostalmanagement.Web.Application.util.TokenType;
import com.hostalmanagement.Web.Application.webconfig.JwtService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    private final EmailService emailService;

    private final SaveCodeRepo saveCodeRepo;

    private User user;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;


    @Transactional
    public ResponseEntity<Map<String, String>> register(RegistrationRequest registrationRequest) throws MessagingException {


        Optional<StudentMailStore> studentMailStore = studentMailRepository.findByEmail(registrationRequest.getEmail());

        if(studentMailStore.isPresent()){
            Optional<User> exsistUser = userRepository.findByEmail(registrationRequest.getEmail());

            if(exsistUser.isPresent()){
                System.out.println("User Already Exsist");
                return ResponseEntity.ok(Collections.singletonMap("message", "User Already Exsist"));

            }

            var user = User.builder()
                    .firstname(registrationRequest.getFirstname())
                    .lastname(registrationRequest.getLastname())
                    .email(registrationRequest.getEmail())
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .role(Role.STUDENT)
                    .build();

                this.user =user;
                sendValidationEmail(user);


            return ResponseEntity.ok(Collections.singletonMap("message", "OTP"));

        }else {
            System.out.println("Your Email is Not Registered In The System Yet");
            return ResponseEntity.ok(Collections.singletonMap("message", "Your Email is Not Registered In The System Yet"));

        }
    }


    private void sendValidationEmail(User user) throws MessagingException {

        String code = generateActivationCode(6);

        emailService.sendUserCredentials(
                user.getEmail(),
                user.getFirstname()+" "+user.getLastname(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                code,
                "Account Activation");

        var savedCode = SaveActivatedCoeds.builder()
                .activationCode(code).build();

        saveCodeRepo.save(savedCode);
    }

    private String generateActivationCode(int length) {

        String characters = "0123456789";

        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for(int i = 0 ; i<length ;i++){

            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
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
    public void revokeAllUserTokens(User user) {

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
                        .tgnumber("TG"+i)
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

    @Transactional
    public void activateAccount(String code) {

        SaveActivatedCoeds saveActivatedCoeds = saveCodeRepo.findByActivationCode(code)
                .orElseThrow(()-> new RuntimeException("Invalid Code..."));

        if(saveActivatedCoeds.getActivationCode().contains(code)){

            var savedUser = userRepository.save(this.user);
            var jwtToken = jwtService.generateToken(this.user);
            saveUserToken(savedUser, jwtToken);
        }

    }
}

