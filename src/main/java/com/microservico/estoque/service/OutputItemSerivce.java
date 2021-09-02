package com.microservico.estoque.service;

import com.microservico.estoque.domain.OutputItem;
import com.microservico.estoque.repository.OutputItemRepository;
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
public class OutputItemSerivce implements AbstractService<OutputItem> {

    @Autowired
    private OutputItemRepository outputItemRepository;

    @Override
    public Optional<OutputItem> findByCode(Long code) {
        return this.outputItemRepository.findById(code);
    }

    @Override
    public Collection<OutputItem> findAll() {
        return this.outputItemRepository.findAll();
    }

    @Override
    public OutputItem save(OutputItem itemEntrada) {
        return this.outputItemRepository.save(itemEntrada);
    }

    @Override
    public void delete(Long code) {
        outputItemRepository.findById(code).ifPresent(itemSaida -> outputItemRepository.delete(itemSaida));
    }

    public OutputItem edit(OutputItem outputItem) {
        Optional<OutputItem> itemSaidaSalva = findByCode(outputItem.getCodigoItemSaida());
        if (itemSaidaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(outputItem, itemSaidaSalva, "codigoItemSaida");
        return this.outputItemRepository.save(itemSaidaSalva.get());
    }

}
