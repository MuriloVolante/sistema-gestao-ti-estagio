package com.gestao.controller;

import com.gestao.model.Ativo;
import com.gestao.repository.AtivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ativos")
@RequiredArgsConstructor
public class AtivoController {

    private final AtivoRepository ativoRepository;

    @GetMapping
    public ResponseEntity<List<Ativo>> listar() {
        return ResponseEntity.ok(ativoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ativo> obterPorId(@PathVariable Long id) {
        Optional<Ativo> ativo = ativoRepository.findById(id);
        return ativo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Ativo>> obterPorStatus(@PathVariable String status) {
        try {
            Ativo.StatusAtivo statusEnum = Ativo.StatusAtivo.valueOf(status.toUpperCase());
            return ResponseEntity.ok(ativoRepository.findByStatus(statusEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Ativo> criar(@RequestBody Ativo ativo) {
        Ativo novoAtivo = ativoRepository.save(ativo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAtivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ativo> atualizar(@PathVariable Long id, @RequestBody Ativo ativoAtualizado) {
        Optional<Ativo> ativoExistente = ativoRepository.findById(id);
        if (ativoExistente.isPresent()) {
            Ativo ativo = ativoExistente.get();
            ativo.setPatrimonio(ativoAtualizado.getPatrimonio());
            ativo.setTipo(ativoAtualizado.getTipo());
            ativo.setMarca(ativoAtualizado.getMarca());
            ativo.setModelo(ativoAtualizado.getModelo());
            ativo.setSerie(ativoAtualizado.getSerie());
            ativo.setIp(ativoAtualizado.getIp());
            ativo.setMac(ativoAtualizado.getMac());
            ativo.setStatus(ativoAtualizado.getStatus());
            ativo.setValor(ativoAtualizado.getValor());
            ativo.setResponsavelId(ativoAtualizado.getResponsavelId());
            Ativo ativoSalvo = ativoRepository.save(ativo);
            return ResponseEntity.ok(ativoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (ativoRepository.existsById(id)) {
            ativoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
