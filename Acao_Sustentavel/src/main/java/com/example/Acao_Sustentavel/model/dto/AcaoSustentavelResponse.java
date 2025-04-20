package com.example.Acao_Sustentavel.model.dto;

import com.example.Acao_Sustentavel.model.entity.AcaoSustentavel;

import java.time.LocalDate;

public class AcaoSustentavelResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String categoria;
    private LocalDate dataRealizacao;
    private String responsavel;

    public AcaoSustentavelResponse(AcaoSustentavel acaoSustentavel) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
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
