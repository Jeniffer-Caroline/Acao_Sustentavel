package com.example.Acao_Sustentavel.api_exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
