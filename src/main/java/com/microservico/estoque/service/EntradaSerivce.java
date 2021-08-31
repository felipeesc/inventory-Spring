package com.microservico.estoque.service;

import com.microservico.estoque.domain.Entrada;
import com.microservico.estoque.repository.EntradaRepository;
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
public class EntradaSerivce implements AbstractService<Entrada> {

    @Autowired
    private EntradaRepository entradaRepository;

    @Override
    public Optional<Entrada> findByCode(Long code) {
        return this.entradaRepository.findById(code);
    }

    @Override
    public Collection<Entrada> findAll() {
        return this.entradaRepository.findAll();
    }

    @Override
    public Entrada save(Entrada entrada) {
        return this.entradaRepository.save(entrada);
    }

    @Override
    public void delete(Long code) {
        entradaRepository.findById(code).ifPresent(entrada -> {
            entradaRepository.delete(entrada);
        });
    }

    public Entrada edit(Entrada entrada) {
        Optional<Entrada> entradaSalva = findByCode(entrada.getCodigoEntrada());
        if (entradaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(entrada, entradaSalva, "codigoEntrada");
        return this.entradaRepository.save(entradaSalva.get());
    }

}
