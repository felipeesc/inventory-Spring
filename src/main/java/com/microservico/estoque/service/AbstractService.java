package com.microservico.estoque.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface AbstractService<T>  {

    @Transactional(readOnly = true)
    Optional<T> findByCode(Long code);

    @Transactional(readOnly = true)
    Collection<T> findAll();

    @Transactional
    T save(T dataToSave);

    @Transactional
    void delete(Long code);
}
