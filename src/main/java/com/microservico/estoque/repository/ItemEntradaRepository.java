package com.microservico.estoque.repository;

import com.microservico.estoque.domain.Cidade;
import com.microservico.estoque.domain.ItemEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEntradaRepository extends JpaRepository<ItemEntrada, Long>, JpaSpecificationExecutor<ItemEntrada> {

}
