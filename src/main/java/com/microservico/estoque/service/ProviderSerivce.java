package com.microservico.estoque.service;

import com.microservico.estoque.domain.Provider;
import com.microservico.estoque.repository.ProviderRepository;
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
public class ProviderSerivce implements AbstractService<Provider> {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Optional<Provider> findByCode(Long code) {
        return this.providerRepository.findById(code);
    }

    @Override
    public Collection<Provider> findAll() {
        return this.providerRepository.findAll();
    }

    @Override
    public Provider save(Provider provider) {
        return this.providerRepository.save(provider);
    }

    @Override
    public void delete(Long code) {
        providerRepository.findById(code).ifPresent(provider -> {
            providerRepository.delete(provider);
        });
    }

    public Provider edit(Provider provider) {
        Optional<Provider> fornecedorSalvo = findByCode(provider.getCodigoFornecedor());
        if (fornecedorSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(provider, fornecedorSalvo, "codigoFornecedor");
        return this.providerRepository.save(fornecedorSalvo.get());
    }

}
