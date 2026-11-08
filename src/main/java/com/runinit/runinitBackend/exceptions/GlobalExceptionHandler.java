package com.runinit.runinitBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(erro -> erros.put(erro.getField(), erro.getDefaultMessage()));

        Map<String, Object> corpo = new HashMap<>();
        corpo.put("status", HttpStatus.BAD_REQUEST.value());
        corpo.put("erros", erros);

        return ResponseEntity.badRequest().body(corpo);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<Map<String, Object>> tratarEmailDuplicado(EmailJaCadastradoException ex) {
        Map<String, Object> corpo = new HashMap<>();
        corpo.put("status", HttpStatus.CONFLICT.value());
        corpo.put("erros", Map.of("email", ex.getMessage()));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(corpo);
    }
}