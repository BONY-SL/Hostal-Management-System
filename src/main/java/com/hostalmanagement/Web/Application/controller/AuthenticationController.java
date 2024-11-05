package com.hostalmanagement.Web.Application.controller;
import com.hostalmanagement.Web.Application.dto.AuthenticationRequest;
import com.hostalmanagement.Web.Application.dto.AuthenticationResponse;
import com.hostalmanagement.Web.Application.dto.RegistrationRequest;
import com.hostalmanagement.Web.Application.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/hostalmanage/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<ResponseEntity<Map<String, String>>> register(
            @RequestBody RegistrationRequest request
    ) throws Exception {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }


    @GetMapping("/activate-account")
    public void confirm(@RequestParam String code) throws MessagingException {

        authenticationService.activateAccount(code);
    }





}
