package com.microservico.estoque.repository;

import com.microservico.estoque.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

}
