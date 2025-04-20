package com.example.Acao_Sustentavel.Controller;


import com.example.Acao_Sustentavel.model.entity.AcaoSustentavel;
import com.example.Acao_Sustentavel.model.dto.AcaoSustentavelRequest;
import com.example.Acao_Sustentavel.model.dto.AcaoSustentavelResponse;
import com.example.Acao_Sustentavel.service.AcaoSustentavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class AcaoSustentavelController {
    @Autowired
    private AcaoSustentavelService acaoSustentavelService;

    @PostMapping("/acao-sustentavel")
    public ResponseEntity<AcaoSustentavelResponse> criarAcaoSustentavel(@Valid @RequestBody AcaoSustentavelRequest request) {
        AcaoSustentavel acaoSustentavel = acaoSustentavelService.criarAcaoSustentavel(request);
        return ResponseEntity.ok(new AcaoSustentavelResponse(acaoSustentavel));
    }
    @GetMapping
    public ResponseEntity<List<AcaoSustentavelResponse>> listarTodas() {
        List<AcaoSustentavel> acoes = acaoSustentavelService.listarTodas();
        List<AcaoSustentavelResponse> responses = acoes.stream()
                .map(AcaoSustentavelResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> buscarPorId(@PathVariable Long id) {
        AcaoSustentavel acao = acaoSustentavelService.buscarPorId(id);
        if (acao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new AcaoSustentavelResponse(acao));
    }

     @PostMapping
        public ResponseEntity<AcaoSustentavelResponse> cadastrarNovaAcao(@Valid @RequestBody  AcaoSustentavelRequest request){
        AcaoSustentavel acao = acaoSustentavelService.cadastrarNovaAcao(request);
        return ResponseEntity.ok(new AcaoSustentavelResponse(acao));

    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> atualizar(@PathVariable Long id, @Valid @RequestBody AcaoSustentavelRequest request ){
        AcaoSustentavel acao = acaoSustentavelService.atualizar(id, request);
        if (acao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new AcaoSustentavelResponse(acao));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Valid> remover (@PathVariable Long id){
        acaoSustentavelService.remover(id);
        return  ResponseEntity.noContent().build();
    }
}
