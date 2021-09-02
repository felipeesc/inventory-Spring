package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.ItemEntrada;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.ItemEntradaSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/item-entrada")
public class InputItemController {

    @Autowired
    private ItemEntradaSerivce itemEntradaSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<ItemEntrada> findByCode(@PathVariable Long code) {
        Optional<ItemEntrada> cityReturned = this.itemEntradaSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemEntrada> createItem(@Valid @RequestBody ItemEntrada item) {
        ItemEntrada itemEntrada = this.itemEntradaSerivce.save(item);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(itemEntrada.getCodigoItemEntrada()).toUri();
        return ResponseEntity.created(uri).body(itemEntrada);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long code) {
        this.itemEntradaSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("item-entrada.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<ItemEntrada> editItem(@Valid @RequestBody ItemEntrada itemEntrada) {
        ItemEntrada itemReturned = this.itemEntradaSerivce.edit(itemEntrada);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("item-entrada editada.", String.valueOf(itemReturned.getCodigoItemEntrada()))).build();
    }
}
