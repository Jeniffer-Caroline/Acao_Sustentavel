package com.example.Acao_Sustentavel.model.dto;

import com.example.Acao_Sustentavel.model.entity.AcaoSustentavel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AcaoSustentavelRequest {

    @NotBlank(message = "")
    @Size(min = 3, max = 100, message = "")
    private String titulo;

    @NotBlank(message = "")
    @Size(min = 10, max = 500, message = "")
    private String descricao;

    @NotBlank(message = "")
    private String categoria;

    @NotNull(message = "")
    @PastOrPresent(message = "")
    private LocalDate dataRealizacao;

    @NotBlank(message = "")
    @Size(min = 3, max = 100, message = "")
    private String responsavel;

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

    public AcaoSustentavel.Categoria getCategoria() {
        return AcaoSustentavel.Categoria.valueOf(categoria);
    }

    public void setCategoria(AcaoSustentavel.Categoria categoria) {
        this.categoria = String.valueOf(categoria);
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
