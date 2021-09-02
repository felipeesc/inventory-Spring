package com.microservico.estoque.repository;

import com.microservico.estoque.domain.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends JpaRepository<Input, Long>, JpaSpecificationExecutor<Input> {

}
