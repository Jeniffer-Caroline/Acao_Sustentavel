package com.example.Acao_Sustentavel.Controller;


import com.example.Acao_Sustentavel.Repository.AcaoSustentavelRepository;
import com.example.Acao_Sustentavel.model.entity.AcaoSustentavel;
import com.example.Acao_Sustentavel.model.dto.AcaoSustentavelRequest;
import com.example.Acao_Sustentavel.model.dto.AcaoSustentavelResponse;
import com.example.Acao_Sustentavel.service.AcaoSustentavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AcaoSustentavelController {

    @GetMapping("/public")
    public String getPublic(){
        return "Público";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public String postAdmin(){
        return "Admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")
    public String putAdmin(){
        return "Admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin")
    public String deleAdmin(){
        return "Admin";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String getUser(){
        return "User";
    }

    @Autowired
    private AcaoSustentavelService acaoSustentavelService;
    @Autowired
    private AcaoSustentavelRepository acaoSustentavelRepository;

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
    @GetMapping("/categoria")
    public ResponseEntity<List<AcaoSustentavelResponse>> filtrarAcoesPorCategoria(@RequestParam("tipo") @Valid AcaoSustentavel.Categoria categoria){

        if (categoria == null){
            return ResponseEntity.badRequest().build();
        }
        List<AcaoSustentavel> acoes = acaoSustentavelRepository.findByCategoria(categoria);
        List<AcaoSustentavelResponse> responses = acoes.stream()
                .map(AcaoSustentavelResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);

    }
}
