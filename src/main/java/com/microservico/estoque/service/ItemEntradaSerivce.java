package com.microservico.estoque.service;

import com.microservico.estoque.domain.ItemEntrada;
import com.microservico.estoque.repository.ItemEntradaRepository;
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
public class ItemEntradaSerivce implements AbstractService<ItemEntrada> {

    @Autowired
    private ItemEntradaRepository itemEntradaRepository;

    @Override
    public Optional<ItemEntrada> findByCode(Long code) {
        return this.itemEntradaRepository.findById(code);
    }

    @Override
    public Collection<ItemEntrada> findAll() {
        return this.itemEntradaRepository.findAll();
    }

    @Override
    public ItemEntrada save(ItemEntrada itemEntrada) {
        return this.itemEntradaRepository.save(itemEntrada);
    }

    @Override
    public void delete(Long code) {
        itemEntradaRepository.findById(code).ifPresent(itemEntrada -> {
            itemEntradaRepository.delete(itemEntrada);
        });
    }

    public ItemEntrada edit(ItemEntrada itemEntrada) {
        Optional<ItemEntrada> itemEntradaSalva = findByCode(itemEntrada.getCodigoItemEntrada());
        if (itemEntradaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(itemEntrada, itemEntradaSalva, "codigoItemEntrada");
        return this.itemEntradaRepository.save(itemEntradaSalva.get());
    }

}
