package com.runinit.runinitBackend.dtos;

import lombok.Data;

@Data
public class RedefinirSenhaRequest {
    private String token;
    private String novaSenha;
}