package com.runinit.runinitBackend.services;

import com.runinit.runinitBackend.dtos.AuthResponse;
import com.runinit.runinitBackend.dtos.LoginRequest;
import com.runinit.runinitBackend.dtos.RegisterRequest;
import com.runinit.runinitBackend.entities.Usuario;
import com.runinit.runinitBackend.repositories.UsuarioRepository;
import com.runinit.runinitBackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        Usuario user = new Usuario();
        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setSenhaHash(passwordEncoder.encode(request.getSenha()));
        user.setDataNascimento(request.getDataNascimento());

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}