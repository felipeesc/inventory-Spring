package com.microservico.estoque.service;

import com.microservico.estoque.domain.Cidade;
import com.microservico.estoque.repository.CidadeRepository;
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
public class CidadeSerivce implements AbstractService<Cidade> {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public Optional<Cidade> findByCode(Long code) {
        return this.cidadeRepository.findById(code);
    }

    @Override
    public Collection<Cidade> findAll() {
        return this.cidadeRepository.findAll();
    }

    @Override
    public Cidade save(Cidade cidade) {
        return this.cidadeRepository.save(cidade);
    }

    @Override
    public void delete(Long code) {
        cidadeRepository.findById(code).ifPresent(cidade -> {
            cidadeRepository.delete(cidade);
        });
    }

    public Cidade edit(Cidade cidade) {
        Optional<Cidade> cidadeSalva = findByCode(cidade.getCodigoCidade());
        if (cidadeSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(cidade, cidadeSalva, "codigoCidade");
        return this.cidadeRepository.save(cidadeSalva.get());
    }

}
