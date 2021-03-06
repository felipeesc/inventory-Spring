package com.microservico.estoque.repository;

import com.microservico.estoque.domain.InputItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InputItemRepository extends JpaRepository<InputItem, Long>, JpaSpecificationExecutor<InputItem> {

}
