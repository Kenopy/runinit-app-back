package com.runinit.runinitBackend.services;

import com.runinit.runinitBackend.entities.TokenRecuperacaoSenha;
import com.runinit.runinitBackend.entities.Usuario;
import com.runinit.runinitBackend.repositories.TokenRecuperacaoSenhaRepository;
import com.runinit.runinitBackend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecuperacaoSenhaService {

    private final UsuarioRepository usuarioRepository;
    private final TokenRecuperacaoSenhaRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public void solicitarRecuperacao(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        TokenRecuperacaoSenha token = new TokenRecuperacaoSenha();
        token.setUsuario(usuario);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiraEm(LocalDateTime.now().plusHours(1));

        tokenRepository.save(token);
    }

    public void redefinirSenha(String tokenStr, String novaSenha) {
        TokenRecuperacaoSenha token = tokenRepository.findByToken(tokenStr)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (token.getExpiraEm().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("O token expirou");
        }

        Usuario usuario = token.getUsuario();
        usuario.setSenhaHash(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);

        tokenRepository.delete(token);
    }
}