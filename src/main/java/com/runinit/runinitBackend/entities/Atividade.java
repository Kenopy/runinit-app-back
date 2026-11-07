package com.runinit.runinitBackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "atividades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "tipo_atividade", nullable = false)
    private String tipoAtividade;

    @Column(name = "data_atividade", nullable = false)
    private LocalDateTime dataAtividade;

    @Column(name = "duracao_segundos", nullable = false)
    private Integer duracaoSegundos;

    @Column(name = "distancia_metros", nullable = false)
    private BigDecimal distanciaMetros;

    private Integer calorias;

    private Integer passos;

    @Column(name = "fonte_externa")
    private String fonteExterna = "MANUAL";

    @Column(name = "id_externo")
    private String idExterno;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "metricas_extras", columnDefinition = "jsonb")
    private Map<String, Object> metricasExtras;

    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;
}