package com.microservico.estoque.repository;

import com.microservico.estoque.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    String AUTHORITIES = "authorities";

    Optional<User> findOneByLogin(String login);

    @EntityGraph(attributePaths = AUTHORITIES)
    User findOneWithAuthoritiesByCode(Long code);

    @EntityGraph(attributePaths = AUTHORITIES)
    Optional<User> findOneWithAuthoritiesByLogin(String login);
}
