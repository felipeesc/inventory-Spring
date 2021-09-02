package com.microservico.estoque.service;

import com.microservico.estoque.domain.Input;
import com.microservico.estoque.repository.InputRepository;
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
public class InputSerivce implements AbstractService<Input> {

    @Autowired
    private InputRepository inputRepository;

    @Override
    public Optional<Input> findByCode(Long code) {
        return this.inputRepository.findById(code);
    }

    @Override
    public Collection<Input> findAll() {
        return this.inputRepository.findAll();
    }

    @Override
    public Input save(Input input) {
        return this.inputRepository.save(input);
    }

    @Override
    public void delete(Long code) {
        inputRepository.findById(code).ifPresent(entrada -> {
            inputRepository.delete(entrada);
        });
    }

    public Input edit(Input input) {
        Optional<Input> entradaSalva = findByCode(input.getCodigoEntrada());
        if (entradaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(input, entradaSalva, "codigoEntrada");
        return this.inputRepository.save(entradaSalva.get());
    }

}
