package com.srberlin.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srberlin.security.dtos.LoginDto;
import com.srberlin.security.dtos.LoginResponseDto;
import com.srberlin.security.dtos.UserDto;
import com.srberlin.security.network.Response;
import com.srberlin.security.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<LoginResponseDto>> register(
            @RequestBody UserDto registerRequest) {
        LoginResponseDto loginResponse = authService.register(registerRequest);
        return ResponseEntity
                .ok(new Response<LoginResponseDto>().success(loginResponse, "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponseDto>> login(
            @RequestBody LoginDto loginDto) {
        LoginResponseDto loginResponse = authService.login(loginDto);
        return ResponseEntity.ok(new Response<LoginResponseDto>().success(loginResponse, "Login successful"));
    }
}
