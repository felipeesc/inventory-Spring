package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.OutputItem;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.OutputItemSerivce;
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
    private OutputItemSerivce itemSaidaSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<OutputItem> findByCode(@PathVariable Long code) {
        Optional<OutputItem> itemReturned = this.itemSaidaSerivce.findByCode(code);
        return itemReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OutputItem> createItemExit(@Valid @RequestBody OutputItem item) {
        OutputItem outputItem = this.itemSaidaSerivce.save(item);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(outputItem.getCodigoItemSaida()).toUri();
        return ResponseEntity.created(uri).body(outputItem);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteItemExit(@PathVariable Long code) {
        this.itemSaidaSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("item-saida.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<OutputItem> editItemExit(@Valid @RequestBody OutputItem outputItem) {
        OutputItem itemReturned = this.itemSaidaSerivce.edit(outputItem);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("item-saida editada.", String.valueOf(itemReturned.getCodigoItemSaida()))).build();
    }
}
