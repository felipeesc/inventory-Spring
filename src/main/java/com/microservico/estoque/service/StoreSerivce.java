package com.microservico.estoque.service;

import com.microservico.estoque.domain.Loja;
import com.microservico.estoque.repository.LojaRepository;
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
public class StoreSerivce implements AbstractService<Loja> {

    @Autowired
    private LojaRepository lojaRepository;

    @Override
    public Optional<Loja> findByCode(Long code) {
        return this.lojaRepository.findById(code);
    }

    @Override
    public Collection<Loja> findAll() {
        return this.lojaRepository.findAll();
    }

    @Override
    public Loja save(Loja loja) {
        return this.lojaRepository.save(loja);
    }

    @Override
    public void delete(Long code) {
        lojaRepository.findById(code).ifPresent(loja -> {
            lojaRepository.delete(loja);
        });
    }

    public Loja edit(Loja loja) {
        Optional<Loja> lojaSalvo = findByCode(loja.getCodigoLoja());
        if (lojaSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(loja, lojaSalvo, "codigoLoja");
        return this.lojaRepository.save(lojaSalvo.get());
    }

}
