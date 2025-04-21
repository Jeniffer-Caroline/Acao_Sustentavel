package com.example.Acao_Sustentavel.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "acao_sustentavel")
public class AcaoSustentavel {
    public enum Categoria {

            DOACAO,
            VOLUNTARIO,
            EDUCACAO,
            RECICLAGEM,
            PLANTIO,
            EDUCACAO_AMBIENTAL,
            OUTROS
        }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)

    private Categoria categoria;
    private LocalDate dataRealizacao;
    private String responsavel;

public AcaoSustentavel(){}
public AcaoSustentavel(String titulo, String descricao, Categoria categoria, LocalDate dataRealizacao, String responsavel){
    this.titulo = titulo;
    this.descricao = descricao;
    this.categoria = categoria;
    this.dataRealizacao = dataRealizacao;
    this.responsavel = responsavel;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    }
