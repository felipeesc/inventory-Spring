package com.microservico.estoque.repository;

import com.microservico.estoque.domain.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRepository extends JpaRepository<Output, Long>, JpaSpecificationExecutor<Output> {

}
