package com.runinit.runinitBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeRequest {
    private String tipoAtividade;
    private LocalDateTime dataAtividade;
    private Integer duracaoSegundos;
    private BigDecimal distanciaMetros;
}