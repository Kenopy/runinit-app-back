package com.runinit.runinitBackend.services;

import com.runinit.runinitBackend.dtos.AtividadeRequest;
import com.runinit.runinitBackend.entities.Atividade;
import com.runinit.runinitBackend.entities.Usuario;
import com.runinit.runinitBackend.repositories.AtividadeRepository;
import com.runinit.runinitBackend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final UsuarioRepository usuarioRepository;

    public Atividade registrarAtividade(AtividadeRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        Atividade atividade = new Atividade();
        atividade.setUsuario(usuario);
        atividade.setTipoAtividade(request.getTipoAtividade());
        atividade.setDataAtividade(request.getDataAtividade());
        atividade.setDuracaoSegundos(request.getDuracaoSegundos());
        atividade.setDistanciaMetros(request.getDistanciaMetros());
        atividade.setFonteExterna("MANUAL");

        return atividadeRepository.save(atividade);
    }

    public List<Atividade> listarMinhasAtividades() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        return atividadeRepository.findByUsuarioId(usuario.getId());
    }
}