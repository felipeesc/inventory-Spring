package com.microservico.estoque.repository;

import com.microservico.estoque.domain.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long>, JpaSpecificationExecutor<EmailModel> {

}
