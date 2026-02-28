package com.gestao.controller;

import com.gestao.model.Manutencao;
import com.gestao.repository.ManutencaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manutencoes")
@RequiredArgsConstructor
public class ManutencaoController {

    private final ManutencaoRepository manutencaoRepository;

    @GetMapping
    public ResponseEntity<List<Manutencao>> listar() {
        return ResponseEntity.ok(manutencaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> obterPorId(@PathVariable Long id) {
        Optional<Manutencao> manutencao = manutencaoRepository.findById(id);
        return manutencao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ativo/{ativoId}")
    public ResponseEntity<List<Manutencao>> obterPorAtivo(@PathVariable Long ativoId) {
        return ResponseEntity.ok(manutencaoRepository.findByAtivoId(ativoId));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Manutencao>> obterPorTipo(@PathVariable String tipo) {
        try {
            Manutencao.TipoManutencao tipoEnum = Manutencao.TipoManutencao.valueOf(tipo.toUpperCase());
            return ResponseEntity.ok(manutencaoRepository.findByTipo(tipoEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Manutencao> criar(@RequestBody Manutencao manutencao) {
        Manutencao novaManutencao = manutencaoRepository.save(manutencao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaManutencao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manutencao> atualizar(@PathVariable Long id, @RequestBody Manutencao manutencaoAtualizada) {
        Optional<Manutencao> manutencaoExistente = manutencaoRepository.findById(id);
        if (manutencaoExistente.isPresent()) {
            Manutencao manutencao = manutencaoExistente.get();
            manutencao.setAtivoId(manutencaoAtualizada.getAtivoId());
            manutencao.setTipo(manutencaoAtualizada.getTipo());
            manutencao.setDescricao(manutencaoAtualizada.getDescricao());
            manutencao.setCusto(manutencaoAtualizada.getCusto());
            manutencao.setTecnicoId(manutencaoAtualizada.getTecnicoId());
            manutencao.setGarantia(manutencaoAtualizada.getGarantia());
            Manutencao manutencaoSalva = manutencaoRepository.save(manutencao);
            return ResponseEntity.ok(manutencaoSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (manutencaoRepository.existsById(id)) {
            manutencaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
