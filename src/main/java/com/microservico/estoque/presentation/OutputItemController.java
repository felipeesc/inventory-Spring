package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.ItemSaida;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.ItemSaidaSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/item-saida")
public class OutputItemController {

    @Autowired
    private ItemSaidaSerivce itemSaidaSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<ItemSaida> findByCode(@PathVariable Long code) {
        Optional<ItemSaida> itemReturned = this.itemSaidaSerivce.findByCode(code);
        return itemReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemSaida> createItemExit(@Valid @RequestBody ItemSaida item) {
        ItemSaida itemSaida = this.itemSaidaSerivce.save(item);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(itemSaida.getCodigoItemSaida()).toUri();
        return ResponseEntity.created(uri).body(itemSaida);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteItemExit(@PathVariable Long code) {
        this.itemSaidaSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("item-saida.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<ItemSaida> editItemExit(@Valid @RequestBody ItemSaida itemSaida) {
        ItemSaida itemReturned = this.itemSaidaSerivce.edit(itemSaida);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("item-saida editada.", String.valueOf(itemReturned.getCodigoItemSaida()))).build();
    }
}
