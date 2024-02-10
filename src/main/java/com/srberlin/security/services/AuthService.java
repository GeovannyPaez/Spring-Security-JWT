package com.srberlin.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srberlin.security.dtos.LoginDto;
import com.srberlin.security.dtos.LoginResponseDto;
import com.srberlin.security.dtos.UserDto;
import com.srberlin.security.enums.UserRole;
import com.srberlin.security.models.UserModel;
import com.srberlin.security.respositories.UserRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
        @Autowired
        private final UserRespository userRespository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        @SuppressWarnings("null")
        public LoginResponseDto register(UserDto registerRequest) {
                UserModel userData = UserModel.builder()
                                .email(registerRequest.getEmail())
                                .password(passwordEncoder.encode(registerRequest.getPassword()))
                                .firstname(registerRequest.getFirstname())
                                .lastname(registerRequest.getLastname())
                                .role(UserRole.USER)
                                .build();

                userRespository.save(userData);
                String jwtToken = jwtService.generateToken(userData);
                return LoginResponseDto.builder()
                                .token(jwtToken)
                                .build();
        }

        public LoginResponseDto login(LoginDto loginDto) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                loginDto.getEmail(),
                                                loginDto.getPassword()));
                UserDetails user = userRespository.findByEmail(loginDto.getEmail()).orElseThrow(
                                () -> new RuntimeException("User not found"));
                String jwtToken = jwtService.generateToken(user);
                return LoginResponseDto.builder()
                                .token(jwtToken)
                                .build();
        }

}
