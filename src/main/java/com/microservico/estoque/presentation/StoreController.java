package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Store;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.StoreSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.Cacheable;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/loja")
public class StoreController {

    @Autowired
    private StoreSerivce storeSerivce;

    @GetMapping("/{code}")
    @Cacheable("store")
    public ResponseEntity<Store> findByCode(@PathVariable Long code) {
        Optional<Store> cityReturned = this.storeSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @CacheEvict("store")
    public ResponseEntity<Store> createProduct(@Valid @RequestBody Store store) {
        Store storeCreate = this.storeSerivce.save(store);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(storeCreate.getCodigoLoja()).toUri();
        return ResponseEntity.created(uri).body(storeCreate);
    }

    @PostMapping("/{code}")
    @CacheEvict("store")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long code) {
        this.storeSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("store.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    @CacheEvict("store")
    public ResponseEntity<Store> editProduct(@Valid @RequestBody Store store) {
        Store storeReturned = this.storeSerivce.edit(store);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("store.edited", String.valueOf(storeReturned.getCodigoLoja()))).build();
    }
}
