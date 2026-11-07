package com.runinit.runinitBackend.controllers;

import com.runinit.runinitBackend.dtos.RedefinirSenhaRequest;
import com.runinit.runinitBackend.dtos.SolicitarRecuperacaoRequest;
import com.runinit.runinitBackend.services.RecuperacaoSenhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/recuperacao")
@RequiredArgsConstructor
public class RecuperacaoSenhaController {

    private final RecuperacaoSenhaService recuperacaoService;

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitar(@RequestBody SolicitarRecuperacaoRequest request) {
        recuperacaoService.solicitarRecuperacao(request.getEmail());
        return ResponseEntity.ok("Instruções de recuperação enviadas para o e-mail.");
    }

    @PostMapping("/redefinir")
    public ResponseEntity<String> redefinir(@RequestBody RedefinirSenhaRequest request) {
        recuperacaoService.redefinirSenha(request.getToken(), request.getNovaSenha());
        return ResponseEntity.ok("Palavra-passe redefinida com sucesso.");
    }
}