package com.example.Acao_Sustentavel.config;

import com.example.Acao_Sustentavel.api_exceptions.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.Acao_Sustentavel.model.dto.ErrorResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErros(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse("Erro de validação", erros);
        return ResponseEntity.badRequest().body(errorResponse);
        }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Recurso não encontrado", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
     @ExceptionHandler(Exception.class)
     public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
         ErrorResponse errorResponse = new ErrorResponse("Erro genérico", Collections.singletonList(ex.getMessage()));
         return ResponseEntity.internalServerError().body(errorResponse);

    }
}
