package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.City;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.ProviderSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedor")
public class ProviderController {

    @Autowired
    private ProviderSerivce providerSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<City> findByCode(@PathVariable Long code) {
        Optional<City> cityReturned = this.providerSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<City> createProvider(@Valid @RequestBody City cidade) {
        City city = this.providerSerivce.save(cidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(city.getCodigoCidade()).toUri();
        return ResponseEntity.created(uri).body(city);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long code) {
        this.providerSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("fornecedor.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<City> editProvider(@Valid @RequestBody City city) {
        City cityReturned = this.providerSerivce.edit(city);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("fornecedor editada.", String.valueOf(cityReturned.getCodigoCidade()))).build();
    }
}
