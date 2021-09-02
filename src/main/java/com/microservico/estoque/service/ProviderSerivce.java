package com.microservico.estoque.service;

import com.microservico.estoque.domain.Fornecedor;
import com.microservico.estoque.repository.FornecedorRepository;
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
public class ProviderSerivce implements AbstractService<Fornecedor> {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public Optional<Fornecedor> findByCode(Long code) {
        return this.fornecedorRepository.findById(code);
    }

    @Override
    public Collection<Fornecedor> findAll() {
        return this.fornecedorRepository.findAll();
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return this.fornecedorRepository.save(fornecedor);
    }

    @Override
    public void delete(Long code) {
        fornecedorRepository.findById(code).ifPresent(fornecedor -> {
            fornecedorRepository.delete(fornecedor);
        });
    }

    public Fornecedor edit(Fornecedor fornecedor) {
        Optional<Fornecedor> fornecedorSalvo = findByCode(fornecedor.getCodigoFornecedor());
        if (fornecedorSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "codigoFornecedor");
        return this.fornecedorRepository.save(fornecedorSalvo.get());
    }

}
