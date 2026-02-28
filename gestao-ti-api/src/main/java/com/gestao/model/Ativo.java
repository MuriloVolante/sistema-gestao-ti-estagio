package com.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ativos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String patrimonio;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String serie;

    private String ip;

    private String mac;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAtivo status = StatusAtivo.ATIVO;

    @Column(nullable = false)
    private BigDecimal valor;

    private Long responsavelId;

    @Column(nullable = false)
    private LocalDateTime dataAquisicao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    public enum StatusAtivo {
        ATIVO, MANUTENCAO, ESTOQUE, DESCARTADO
    }
}
