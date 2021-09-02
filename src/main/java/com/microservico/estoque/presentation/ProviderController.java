package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Cidade;
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
    public ResponseEntity<Cidade> findByCode(@PathVariable Long code) {
        Optional<Cidade> cityReturned = this.providerSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cidade> createProvider(@Valid @RequestBody Cidade cidade) {
        Cidade city = this.providerSerivce.save(cidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(city.getCodigoCidade()).toUri();
        return ResponseEntity.created(uri).body(city);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long code) {
        this.providerSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("fornecedor.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Cidade> editProvider(@Valid @RequestBody Cidade cidade) {
        Cidade cityReturned = this.providerSerivce.edit(cidade);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("fornecedor editada.", String.valueOf(cityReturned.getCodigoCidade()))).build();
    }
}
