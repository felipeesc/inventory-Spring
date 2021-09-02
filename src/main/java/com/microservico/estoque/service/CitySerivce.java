package com.microservico.estoque.service;

import com.microservico.estoque.domain.City;
import com.microservico.estoque.repository.CityRepository;
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
public class CitySerivce implements AbstractService<City> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Optional<City> findByCode(Long code) {
        return this.cityRepository.findById(code);
    }

    @Override
    public Collection<City> findAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public City save(City city) {
        return this.cityRepository.save(city);
    }

    @Override
    public void delete(Long code) {
        cityRepository.findById(code).ifPresent(cidade -> {
            cityRepository.delete(cidade);
        });
    }

    public City edit(City city) {
        Optional<City> cidadeSalva = findByCode(city.getCodigoCidade());
        if (cidadeSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(city, cidadeSalva, "codigoCidade");
        return this.cityRepository.save(cidadeSalva.get());
    }

}
