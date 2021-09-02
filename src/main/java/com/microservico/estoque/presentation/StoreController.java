package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Loja;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.LojaSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/loja")
public class StoreController {

    @Autowired
    private LojaSerivce lojaSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<Loja> findByCode(@PathVariable Long code) {
        Optional<Loja> cityReturned = this.lojaSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Loja> createProduct(@Valid @RequestBody Loja loja) {
        Loja lojaCreate = this.lojaSerivce.save(loja);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(lojaCreate.getCodigoLoja()).toUri();
        return ResponseEntity.created(uri).body(lojaCreate);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long code) {
        this.lojaSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("loja.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Loja> editProduct(@Valid @RequestBody Loja loja) {
        Loja lojaReturned = this.lojaSerivce.edit(loja);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("loja editada.", String.valueOf(lojaReturned.getCodigoLoja()))).build();
    }
}
