package com.hostalmanagement.Web.Application.service;
import com.hostalmanagement.Web.Application.dto.CreateUser;
import com.hostalmanagement.Web.Application.model.User;
import com.hostalmanagement.Web.Application.repository.UserRepository;
import com.hostalmanagement.Web.Application.util.EmailAlreadyExistsException;
import com.hostalmanagement.Web.Application.webconfig.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    @Transactional
    public ResponseEntity<?> createUser(CreateUser createUser) {
        try {
            User user = User.builder()
                    .firstname(createUser.getFirstname())
                    .lastname(createUser.getLastname())
                    .email(createUser.getEmail())
                    .password(passwordEncoder.encode(createUser.getPassword()))
                    .role(createUser.getRole())
                    .build();

            // Call the stored procedure to insert the user in the database
            userRepository.saveUser(
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole().name()
            );

            // Retrieve the saved User entity to ensure it is recognized as persistent
            User savedUser = userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("User creation failed."));

            // Generate JWT token
            var jwtToken = jwtService.generateToken(savedUser);
            authenticationService.saveUserToken(savedUser, jwtToken);

            return ResponseEntity.ok(Collections.singletonMap("message", "User created successfully."));

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());

            // Inspect the cause of the DataAccessException
            Throwable rootCause = e.getRootCause();
            if (rootCause instanceof SQLException sqlException) {

                // Check for specific SQL state or error code
                if ("45000".equals(sqlException.getSQLState())) {

                    throw new EmailAlreadyExistsException("User with this email already exists."); // Throw custom exception
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmailAlreadyExistsException("User with this email already exists."));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Database error occurred: " + e.getMessage());
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new EmailAlreadyExistsException("User with this email already exists."));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new EmailAlreadyExistsException("User with this email already exists."));
        }
    }



}
