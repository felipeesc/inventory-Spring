package com.microservico.estoque.repository;

import com.microservico.estoque.domain.OutputItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputItemRepository extends JpaRepository<OutputItem, Long>, JpaSpecificationExecutor<OutputItem> {

}
