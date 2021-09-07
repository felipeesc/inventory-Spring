package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.City;
import com.microservico.estoque.presentation.openapi.CityControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.CitySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cidade")
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CitySerivce citySerivce;

    @Override
    public ResponseEntity<City> findByCode(@PathVariable Long code) {
        Optional<City> cityReturned = this.citySerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<City> createCity(@Valid @RequestBody City cidade) {
        City city = this.citySerivce.save(cidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(city.getCodigoCidade()).toUri();
        return ResponseEntity.created(uri).body(city);
    }

    @Override
    public ResponseEntity<Void> deleteCity(@PathVariable Long code) {
        this.citySerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("city.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<City> editCity(@Valid @RequestBody City city) {
        City cityReturned = this.citySerivce.edit(city);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("city.edited.", String.valueOf(cityReturned.getCodigoCidade()))).build();
    }
}
