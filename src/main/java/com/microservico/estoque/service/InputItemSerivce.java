package com.microservico.estoque.service;

import com.microservico.estoque.domain.InputItem;
import com.microservico.estoque.repository.InputItemRepository;
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
public class InputItemSerivce implements AbstractService<InputItem> {

    @Autowired
    private InputItemRepository inputItemRepository;

    @Override
    public Optional<InputItem> findByCode(Long code) {
        return this.inputItemRepository.findById(code);
    }

    @Override
    public Collection<InputItem> findAll() {
        return this.inputItemRepository.findAll();
    }

    @Override
    public InputItem save(InputItem inputItem) {
        return this.inputItemRepository.save(inputItem);
    }

    @Override
    public void delete(Long code) {
        inputItemRepository.findById(code).ifPresent(itemEntrada -> {
            inputItemRepository.delete(itemEntrada);
        });
    }

    public InputItem edit(InputItem inputItem) {
        Optional<InputItem> itemEntradaSalva = findByCode(inputItem.getCodigoItemEntrada());
        if (itemEntradaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(inputItem, itemEntradaSalva, "codigoItemEntrada");
        return this.inputItemRepository.save(itemEntradaSalva.get());
    }

}
