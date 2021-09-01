package com.microservico.estoque.service;

import com.microservico.estoque.domain.ItemSaida;
import com.microservico.estoque.repository.ItemSaidaRepository;
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
public class ItemSaidaSerivce implements AbstractService<ItemSaida> {

    @Autowired
    private ItemSaidaRepository itemSaidaRepository;

    @Override
    public Optional<ItemSaida> findByCode(Long code) {
        return this.itemSaidaRepository.findById(code);
    }

    @Override
    public Collection<ItemSaida> findAll() {
        return this.itemSaidaRepository.findAll();
    }

    @Override
    public ItemSaida save(ItemSaida itemEntrada) {
        return this.itemSaidaRepository.save(itemEntrada);
    }

    @Override
    public void delete(Long code) {
        itemSaidaRepository.findById(code).ifPresent(itemSaida -> {
            itemSaidaRepository.delete(itemSaida);
        });
    }

    public ItemSaida edit(ItemSaida itemSaida) {
        Optional<ItemSaida> itemSaidaSalva = findByCode(itemSaida.getCodigoItemSaida());
        if (itemSaidaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(itemSaida, itemSaidaSalva, "codigoItemSaida");
        return this.itemSaidaRepository.save(itemSaidaSalva.get());
    }

}
