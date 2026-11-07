package com.runinit.runinitBackend.controllers;

import com.runinit.runinitBackend.dtos.AtividadeRequest;
import com.runinit.runinitBackend.entities.Atividade;
import com.runinit.runinitBackend.services.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atividades")
@RequiredArgsConstructor
public class AtividadeController {

    private final AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity<Atividade> registrar(@RequestBody AtividadeRequest request) {
        Atividade novaAtividade = atividadeService.registrarAtividade(request);
        return ResponseEntity.ok(novaAtividade);
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> listar() {
        return ResponseEntity.ok(atividadeService.listarMinhasAtividades());
    }
}