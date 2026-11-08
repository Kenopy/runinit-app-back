package com.runinit.runinitBackend.services;

import com.runinit.runinitBackend.dtos.AuthResponse;
import com.runinit.runinitBackend.dtos.LoginRequest;
import com.runinit.runinitBackend.dtos.RegisterRequest;
import com.runinit.runinitBackend.entities.Usuario;
import com.runinit.runinitBackend.exceptions.EmailJaCadastradoException;
import com.runinit.runinitBackend.repositories.UsuarioRepository;
import com.runinit.runinitBackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse registrarUsuario(RegisterRequest request) {
        String email = normalizarEmail(request.getEmail());

        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailJaCadastradoException(email);
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(email);
        usuario.setSenhaHash(passwordEncoder.encode(request.getSenha()));
        usuario.setDataNascimento(request.getDataNascimento());

        Usuario salvo = usuarioRepository.save(usuario);

        String jwtToken = jwtService.generateToken(salvo);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(LoginRequest request) {
        String email = normalizarEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, request.getSenha())
        );

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        String jwtToken = jwtService.generateToken(usuario);
        return new AuthResponse(jwtToken);
    }

    private String normalizarEmail(String email) {
        return email.trim().toLowerCase();
    }
}