package com.runinit.runinitBackend.repositories;

import com.runinit.runinitBackend.entities.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AtividadeRepository extends JpaRepository<Atividade, UUID> {
    List<Atividade> findByUsuarioId(UUID usuarioId);
}