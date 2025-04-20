package com.example.Acao_Sustentavel.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ErrorResponse {
    private String mensagem;
    private List<String> erros;

    public ErrorResponse(String mensagem, List<String> erros) {
        this.mensagem = mensagem;
        this.erros = erros;
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<String> getErros() {
        return erros;
    }
    @Override
    public String toString(){
        return "ErrorResponse{" +
                "mensagem='" + mensagem + '\'' +
                ",erros" + erros +
                '}';
    }
}