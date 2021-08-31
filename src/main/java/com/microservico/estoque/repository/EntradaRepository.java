package com.microservico.estoque.repository;

import com.microservico.estoque.domain.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long>, JpaSpecificationExecutor<Entrada> {

}
