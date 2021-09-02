package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Provider;
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
    public ResponseEntity<Provider> findByCode(@PathVariable Long code) {
        Optional<Provider> cityReturned = this.providerSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider) {
        Provider save = this.providerSerivce.save(provider);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(save.getCodigoFornecedor()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long code) {
        this.providerSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("fornecedor.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Provider> editProvider(@Valid @RequestBody Provider provider) {
        Provider providerReturned = this.providerSerivce.edit(provider);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("fornecedor editada.", String.valueOf(providerReturned.getCodigoFornecedor()))).build();
    }
}
