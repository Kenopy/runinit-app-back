package com.runinit.runinitBackend.controllers;

import com.runinit.runinitBackend.dtos.AuthResponse;
import com.runinit.runinitBackend.dtos.LoginRequest;
import com.runinit.runinitBackend.dtos.RegisterRequest;
import com.runinit.runinitBackend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        var result = ResponseEntity.ok(authService.login(request));
        return result;
    }
}