
package com.example.Acao_Sustentavel.Repository;

import com.example.Acao_Sustentavel.model.entity.AcaoSustentavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcaoSustentavelRepository extends JpaRepository<AcaoSustentavel, Long> {
List<AcaoSustentavel> findByCategoria(AcaoSustentavel.Categoria categoria);

}
