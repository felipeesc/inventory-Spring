package com.microservico.estoque.service;

import com.microservico.estoque.domain.Transport;
import com.microservico.estoque.repository.TransportRepository;
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
public class TransportSerivce implements AbstractService<Transport> {

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public Optional<Transport> findByCode(Long code) {
        return this.transportRepository.findById(code);
    }

    @Override
    public Collection<Transport> findAll() {
        return this.transportRepository.findAll();
    }

    @Override
    public Transport save(Transport transport) {
        return this.transportRepository.save(transport);
    }

    @Override
    public void delete(Long code) {
        transportRepository.findById(code).ifPresent(transport -> {
            transportRepository.delete(transport);
        });
    }

    public Transport edit(Transport transport) {
        Optional<Transport> transportEdit = findByCode(transport.getCodigoTransportadora());
        if (transportEdit.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(transport, transportEdit, "codigoTransportadora");
        return this.transportRepository.save(transportEdit.get());
    }

}
