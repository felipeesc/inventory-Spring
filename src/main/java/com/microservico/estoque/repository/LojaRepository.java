package com.microservico.estoque.repository;

import com.microservico.estoque.domain.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long>, JpaSpecificationExecutor<Loja> {

}
