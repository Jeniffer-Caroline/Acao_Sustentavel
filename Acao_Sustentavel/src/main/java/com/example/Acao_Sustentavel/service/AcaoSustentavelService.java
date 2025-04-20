package com.example.Acao_Sustentavel.service;

import com.example.Acao_Sustentavel.model.entity.AcaoSustentavel;
import com.example.Acao_Sustentavel.model.dto.AcaoSustentavelRequest;
import com.example.Acao_Sustentavel.Repository.AcaoSustentavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcaoSustentavelService {

    @Autowired
    AcaoSustentavelRepository acaoSustentavelRepository;

    public List<AcaoSustentavel> listarTodas(){
        return acaoSustentavelRepository.findAll();
    }
    public AcaoSustentavel buscarPorId(Long id){
        return  acaoSustentavelRepository.findById(id).orElse(null);
    }
    public AcaoSustentavel criarAcaoSustentavel(AcaoSustentavelRequest request){
        AcaoSustentavel acaoSustentavel = new AcaoSustentavel();
        acaoSustentavel.setTitulo(request.getTitulo());
        acaoSustentavel.setDescricao(request.getDescricao());
        acaoSustentavel.setCategoria(request.getCategoria());
        acaoSustentavel.setDataRealizacao(request.getDataRealizacao());
        acaoSustentavel.setResponsavel(request.getResponsavel());
        return  acaoSustentavelRepository.save(acaoSustentavel);
    }

    public AcaoSustentavel cadastrarNovaAcao(AcaoSustentavelRequest request){
        AcaoSustentavel acao = new AcaoSustentavel();
        acao.setTitulo(request.getTitulo());
        acao.setDescricao(request.getDescricao());
        acao.setCategoria(request.getCategoria());
        acao.setDataRealizacao(request.getDataRealizacao());
        acao.setResponsavel(acao.getResponsavel());
        return acaoSustentavelRepository.save(acao);
    }

    public AcaoSustentavel atualizar(Long id, AcaoSustentavelRequest request){
        AcaoSustentavel acao = buscarPorId(id);
        if (acao == null){
            return null;
        }
        acao.setTitulo(request.getTitulo());
        acao.setDescricao(request.getDescricao());
        acao.setCategoria(request.getCategoria());
        acao.setDataRealizacao(request.getDataRealizacao());
        acao.setResponsavel(request.getResponsavel());
        return acaoSustentavelRepository.save(acao);
    }
    public void remover(Long id){
        acaoSustentavelRepository.deleteById(id);
    }

}
