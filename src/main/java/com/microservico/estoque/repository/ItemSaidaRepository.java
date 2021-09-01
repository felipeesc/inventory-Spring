package com.microservico.estoque.repository;

import com.microservico.estoque.domain.ItemSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSaidaRepository extends JpaRepository<ItemSaida, Long>, JpaSpecificationExecutor<ItemSaida> {

}
