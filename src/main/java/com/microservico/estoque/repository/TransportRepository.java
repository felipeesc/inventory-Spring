package com.microservico.estoque.repository;

import com.microservico.estoque.domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long>, JpaSpecificationExecutor<Transport> {

}
