package com.microservico.estoque.service;

import com.microservico.estoque.domain.Store;
import com.microservico.estoque.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class StoreSerivce implements AbstractService<Store> {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Optional<Store> findByCode(Long code) {
        return this.storeRepository.findById(code);
    }

    @Override
    public Collection<Store> findAll() {
        return this.storeRepository.findAll();
    }

    @Override
    public Store save(Store store) {
        return this.storeRepository.save(store);
    }

    @Override
    public void delete(Long code) {
        storeRepository.findById(code).ifPresent(store -> {
            storeRepository.delete(store);
        });
    }

    public Store edit(Store store) {
        Optional<Store> lojaSalvo = findByCode(store.getCodigoLoja());
        if (lojaSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(store, lojaSalvo, "codigoLoja");
        return this.storeRepository.save(lojaSalvo.get());
    }

}
