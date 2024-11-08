package com.hostalmanagement.Web.Application.service;
import com.hostalmanagement.Web.Application.dto.*;
import com.hostalmanagement.Web.Application.model.User;
import com.hostalmanagement.Web.Application.repository.RoomRepository;
import com.hostalmanagement.Web.Application.repository.TokenRepository;
import com.hostalmanagement.Web.Application.repository.UserRepository;
import com.hostalmanagement.Web.Application.util.EmailAlreadyExistsException;
import com.hostalmanagement.Web.Application.util.EmailService;
import com.hostalmanagement.Web.Application.webconfig.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final JdbcTemplate jdbcTemplate;

    private final EmailService emailService;

    private final TokenRepository tokenRepository;

    private final RoomRepository repository;

    @Transactional
    public ResponseEntity<?> createUser(CreateUser createUser) {


        String password = generateDefaultPassword(10);
        try {
            User user = User.builder()
                    .firstname(createUser.getFirstname())
                    .lastname(createUser.getLastname())
                    .email(createUser.getEmail())
                    .password(passwordEncoder.encode(password))
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
            emailService.sendSystemUserCredentials(user.getEmail(),"Hostal Management System UOR FOT",user.getEmail(),password);

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


    private String generateDefaultPassword(int length) {

        String characters = "01Ds3d567xD3";

        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for(int i = 0 ; i<length ;i++){

            String randomIndex = String.valueOf(secureRandom.nextInt(characters.length()));
            codeBuilder.append(characters.charAt(Integer.parseInt(randomIndex)));
        }

        return codeBuilder.toString();
    }


    public List<UserDto> getSystemUsers() {

        List<User> userList = userRepository.getUsersExceptStudent();

        return userList.stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto convertUserToUserDto(User user){

        var getUser = UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole()).build();

        return getUser;
    }

    public ResponseEntity<?> saveStudentEmailAndTgNumbers(List<StudentMailsStoreDTO> studentMailsStoreDTO) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("saveStudentEmails")
                .declareParameters(
                        new SqlParameter("InEmail", Types.VARCHAR),
                        new SqlParameter("IntgNumber", Types.VARCHAR),
                        new SqlOutParameter("StatusMessage", Types.VARCHAR)
                );

        List<String> errorMessages = new ArrayList<>();

        for (StudentMailsStoreDTO storeDTO : studentMailsStoreDTO) {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put("InEmail", storeDTO.getEmail());
            inParams.put("IntgNumber", storeDTO.getTgnumber());

            // Execute stored procedure and get the result
            Map<String, Object> outParams = simpleJdbcCall.execute(inParams);
            String statusMessage = (String) outParams.get("StatusMessage");

            // Check if the status message contains an error
            if (statusMessage.contains("Error")) {
                errorMessages.add(statusMessage + " for email: " + storeDTO.getEmail());
            }
        }

        // If there are errors, return them; otherwise, confirm success
        if (!errorMessages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("errors", errorMessages));
        } else {
            return ResponseEntity.ok(Collections.singletonMap("message", "All emails saved successfully."));
        }
    }


    @Transactional
    public GetAdminProfileDetails getAdminProfileDetailsResponseEntity(Integer id) {

        return userRepository.findAdminDetailsByEmail(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    @Transactional
    public ResponseEntity<Map<String, String>> updateAdminProfile(UpdateAdminDTO updateAdminDTO) {

        String resultMessage = userRepository.updateAdminProfile(
                updateAdminDTO.getId(),
                updateAdminDTO.getFirstname(),
                updateAdminDTO.getLastname(),
                updateAdminDTO.getEmail()
        );

        if ("Profile updated successfully".equals(resultMessage)) {

            User user = userRepository.findById(updateAdminDTO.getId())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            user.setEmail(updateAdminDTO.getEmail());

            // Generate new JWT tokens
            String newAccessToken = jwtService.generateToken(user);
            String newRefreshToken = jwtService.generateRefreshToken(user);
            tokenRepository.updateToken(user.getId(),newAccessToken);

            // Create a response map with tokens
            Map<String, String> response = new HashMap<>();
            response.put("message", "Profile updated successfully");
            response.put("access_token", newAccessToken);
            response.put("refresh_token", newRefreshToken);



            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(Collections.singletonMap("message", resultMessage));
    }


    public boolean checkPassword(Integer userId, String rawPassword) {

        Optional<String> encodedPasswordOpt = userRepository.getEncodedPasswordById(userId);
        return encodedPasswordOpt.isPresent() && passwordEncoder.matches(rawPassword, encodedPasswordOpt.get());
    }


    public ResponseEntity<Map<String, String>> confirmationPassword(ChangePasswordRequest changePasswordRequest) {

        boolean isMatched = checkPassword(changePasswordRequest.getId(), changePasswordRequest.getCurrentPassword());

        Map<String, String> response = new HashMap<>();
        if (isMatched) {
            response.put("status", "Password matched");
        } else {
            response.put("status", "Cannot update. The entered current password does not match.");
        }

        return ResponseEntity.ok(response);
    }


    public boolean updatePassword(ChangePasswordRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {

                // Encode and update the new password
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public String createNewRoom(CreateRoomRequest createRoomRequest) {
        repository.createRoom(
                createRoomRequest.getRoomNumber(),
                createRoomRequest.getFloorNumber(),
                createRoomRequest.getRoomCapacity(),
                createRoomRequest.getDescription(),
                createRoomRequest.getBuildingId()
        );
        return "Room created successfully";
    }
}
