package com.gestao.controller;

import com.gestao.model.Chamado;
import com.gestao.repository.ChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chamados")
@RequiredArgsConstructor
public class ChamadoController {

    private final ChamadoRepository chamadoRepository;

    @GetMapping
    public ResponseEntity<List<Chamado>> listar() {
        return ResponseEntity.ok(chamadoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> obterPorId(@PathVariable Long id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Chamado>> obterPorStatus(@PathVariable String status) {
        try {
            Chamado.StatusChamado statusEnum = Chamado.StatusChamado.valueOf(status.toUpperCase());
            return ResponseEntity.ok(chamadoRepository.findByStatus(statusEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/prioridade/{prioridade}")
    public ResponseEntity<List<Chamado>> obterPorPrioridade(@PathVariable String prioridade) {
        try {
            Chamado.Prioridade prioridadeEnum = Chamado.Prioridade.valueOf(prioridade.toUpperCase());
            return ResponseEntity.ok(chamadoRepository.findByPrioridade(prioridadeEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/solicitante/{solicitanteId}")
    public ResponseEntity<List<Chamado>> obterPorSolicitante(@PathVariable Long solicitanteId) {
        return ResponseEntity.ok(chamadoRepository.findBySolicitanteId(solicitanteId));
    }

    @PostMapping
    public ResponseEntity<Chamado> criar(@RequestBody Chamado chamado) {
        Chamado novoChamado = chamadoRepository.save(chamado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoChamado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chamado> atualizar(@PathVariable Long id, @RequestBody Chamado chamadoAtualizado) {
        Optional<Chamado> chamadoExistente = chamadoRepository.findById(id);
        if (chamadoExistente.isPresent()) {
            Chamado chamado = chamadoExistente.get();
            chamado.setTitulo(chamadoAtualizado.getTitulo());
            chamado.setDescricao(chamadoAtualizado.getDescricao());
            chamado.setPrioridade(chamadoAtualizado.getPrioridade());
            chamado.setStatus(chamadoAtualizado.getStatus());
            chamado.setTecnicoId(chamadoAtualizado.getTecnicoId());
            Chamado chamadoSalvo = chamadoRepository.save(chamado);
            return ResponseEntity.ok(chamadoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (chamadoRepository.existsById(id)) {
            chamadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
