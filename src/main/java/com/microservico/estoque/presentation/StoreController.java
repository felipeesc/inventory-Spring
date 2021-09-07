package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Store;
import com.microservico.estoque.presentation.openapi.StoreControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.StoreSerivce;
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
@RequestMapping("/loja")
public class StoreController implements StoreControllerOpenApi {

    @Autowired
    private StoreSerivce storeSerivce;

    @Override
    public ResponseEntity<Store> findByCode(@PathVariable Long code) {
        Optional<Store> cityReturned = this.storeSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Store> createStore(@Valid @RequestBody Store store) {
        Store storeCreate = this.storeSerivce.save(store);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(storeCreate.getCodigoLoja()).toUri();
        return ResponseEntity.created(uri).body(storeCreate);
    }

    @Override
    public ResponseEntity<Void> deleteStore(@PathVariable Long code) {
        this.storeSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("store.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<Store> editStore(@Valid @RequestBody Store store) {
        Store storeReturned = this.storeSerivce.edit(store);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("store.edited", String.valueOf(storeReturned.getCodigoLoja()))).build();
    }
}
