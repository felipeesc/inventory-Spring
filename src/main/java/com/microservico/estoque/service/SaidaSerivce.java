package com.microservico.estoque.service;

import com.microservico.estoque.domain.Saida;
import com.microservico.estoque.repository.SaidaRepository;
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
public class SaidaSerivce implements AbstractService<Saida> {

    @Autowired
    private SaidaRepository SaidaRepository;

    @Override
    public Optional<Saida> findByCode(Long code) {
        return this.SaidaRepository.findById(code);
    }

    @Override
    public Collection<Saida> findAll() {
        return this.SaidaRepository.findAll();
    }

    @Override
    public Saida save(Saida saida) {
        return this.SaidaRepository.save(saida);
    }

    @Override
    public void delete(Long code) {
        SaidaRepository.findById(code).ifPresent(saida -> {
            SaidaRepository.delete(saida);
        });
    }

    public Saida edit(Saida saida) {
        Optional<Saida> saidaSalva = findByCode(saida.getCodigoSaida());
        if (saidaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(saida, saidaSalva, "codigoSaida");
        return this.SaidaRepository.save(saidaSalva.get());
    }

}
