package com.microservico.estoque.repository;

import com.microservico.estoque.domain.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Long>, JpaSpecificationExecutor<Saida> {

}
