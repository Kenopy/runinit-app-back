package com.runinit.runinitBackend.controllers;

import com.runinit.runinitBackend.dtos.AuthResponse;
import com.runinit.runinitBackend.dtos.LoginRequest;
import com.runinit.runinitBackend.dtos.RegisterRequest;
import com.runinit.runinitBackend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registroUsuario")
    public ResponseEntity<AuthResponse> registroUsuario(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.registrarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        var result = ResponseEntity.ok(authService.login(request));
        return result;
    }
}