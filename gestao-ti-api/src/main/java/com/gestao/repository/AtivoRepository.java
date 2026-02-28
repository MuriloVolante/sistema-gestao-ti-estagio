package com.gestao.repository;

import com.gestao.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    List<Ativo> findByStatus(Ativo.StatusAtivo status);
    Ativo findByPatrimonio(String patrimonio);
}
