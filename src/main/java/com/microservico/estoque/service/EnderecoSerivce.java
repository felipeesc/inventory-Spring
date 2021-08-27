package com.microservico.estoque.service;

import com.microservico.estoque.domain.Categoria;
import com.microservico.estoque.domain.Endereco;
import com.microservico.estoque.repository.CategoriaRepository;
import com.microservico.estoque.repository.EnderecoRepository;
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
public class EnderecoSerivce implements AbstractService<Endereco> {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Optional<Endereco> findByCode(Long code) {
        return this.enderecoRepository.findById(code);
    }

    @Override
    public Collection<Endereco> findAll() {
        return this.enderecoRepository.findAll();
    }

    @Override
    public Endereco save(Endereco endereco) {
        return this.enderecoRepository.save(endereco);
    }

    @Override
    public void delete(Long code) {
        enderecoRepository.findById(code).ifPresent(endereco -> {
            enderecoRepository.delete(endereco);
        });
    }

    public Endereco edit(Endereco endereco) {
        Optional<Endereco> enderecoSalvo = findByCode(endereco.getId());
        if (enderecoSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(endereco, enderecoSalvo, "code");
        return this.enderecoRepository.save(enderecoSalvo.get());
    }

}
