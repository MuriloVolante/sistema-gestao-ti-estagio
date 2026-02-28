package com.gestao.repository;

import com.gestao.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByStatus(Chamado.StatusChamado status);
    List<Chamado> findByPrioridade(Chamado.Prioridade prioridade);
    List<Chamado> findBySolicitanteId(Long solicitanteId);
    List<Chamado> findByTecnicoId(Long tecnicoId);
}
