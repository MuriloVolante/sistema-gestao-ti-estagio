package com.gestao.controller;

import com.gestao.model.Movimentacao;
import com.gestao.repository.MovimentacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoRepository movimentacaoRepository;

    @GetMapping
    public ResponseEntity<List<Movimentacao>> listar() {
        return ResponseEntity.ok(movimentacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> obterPorId(@PathVariable Long id) {
        Optional<Movimentacao> movimentacao = movimentacaoRepository.findById(id);
        return movimentacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ativo/{ativoId}")
    public ResponseEntity<List<Movimentacao>> obterPorAtivo(@PathVariable Long ativoId) {
        return ResponseEntity.ok(movimentacaoRepository.findByAtivoId(ativoId));
    }

    @PostMapping
    public ResponseEntity<Movimentacao> criar(@RequestBody Movimentacao movimentacao) {
        Movimentacao novaMovimentacao = movimentacaoRepository.save(movimentacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMovimentacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> atualizar(@PathVariable Long id, @RequestBody Movimentacao movimentacaoAtualizada) {
        Optional<Movimentacao> movimentacaoExistente = movimentacaoRepository.findById(id);
        if (movimentacaoExistente.isPresent()) {
            Movimentacao movimentacao = movimentacaoExistente.get();
            movimentacao.setAtivoId(movimentacaoAtualizada.getAtivoId());
            movimentacao.setResponsavelAntigoId(movimentacaoAtualizada.getResponsavelAntigoId());
            movimentacao.setResponsavelNovoId(movimentacaoAtualizada.getResponsavelNovoId());
            movimentacao.setCentroCusto(movimentacaoAtualizada.getCentroCusto());
            movimentacao.setMotivo(movimentacaoAtualizada.getMotivo());
            Movimentacao movimentacaoSalva = movimentacaoRepository.save(movimentacao);
            return ResponseEntity.ok(movimentacaoSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (movimentacaoRepository.existsById(id)) {
            movimentacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
