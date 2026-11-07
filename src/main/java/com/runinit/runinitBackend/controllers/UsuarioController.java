package com.runinit.runinitBackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @GetMapping("/perfil")
    public ResponseEntity<String> obterPerfil(Authentication authentication) {
        return ResponseEntity.ok("Acesso Autorizado! Você está logado como: " + authentication.getName());
    }
}