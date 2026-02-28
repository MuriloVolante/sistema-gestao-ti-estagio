package com.gestao.controller;

import com.gestao.model.Comentario;
import com.gestao.repository.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<List<Comentario>> listar() {
        return ResponseEntity.ok(comentarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obterPorId(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        return comentario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/chamado/{chamadoId}")
    public ResponseEntity<List<Comentario>> obterPorChamado(@PathVariable Long chamadoId) {
        return ResponseEntity.ok(comentarioRepository.findByChamadoId(chamadoId));
    }

    @PostMapping
    public ResponseEntity<Comentario> criar(@RequestBody Comentario comentario) {
        Comentario novoComentario = comentarioRepository.save(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoComentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @RequestBody Comentario comentarioAtualizado) {
        Optional<Comentario> comentarioExistente = comentarioRepository.findById(id);
        if (comentarioExistente.isPresent()) {
            Comentario comentario = comentarioExistente.get();
            comentario.setConteudo(comentarioAtualizado.getConteudo());
            Comentario comentarioSalvo = comentarioRepository.save(comentario);
            return ResponseEntity.ok(comentarioSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
