package com.microservico.estoque.service;

import com.microservico.estoque.domain.Output;
import com.microservico.estoque.repository.OutputRepository;
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
public class OutputSerivce implements AbstractService<Output> {

    @Autowired
    private OutputRepository OutputRepository;

    @Override
    public Optional<Output> findByCode(Long code) {
        return this.OutputRepository.findById(code);
    }

    @Override
    public Collection<Output> findAll() {
        return this.OutputRepository.findAll();
    }

    @Override
    public Output save(Output output) {
        return this.OutputRepository.save(output);
    }

    @Override
    public void delete(Long code) {
        OutputRepository.findById(code).ifPresent(output -> {
            OutputRepository.delete(output);
        });
    }

    public Output edit(Output output) {
        Optional<Output> saidaSalva = findByCode(output.getCodigoSaida());
        if (saidaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(output, saidaSalva, "codigoSaida");
        return this.OutputRepository.save(saidaSalva.get());
    }

}
