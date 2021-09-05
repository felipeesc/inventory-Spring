package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.InputItem;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.InputItemSerivce;
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
    private InputItemSerivce inputItemSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<InputItem> findByCode(@PathVariable Long code) {
        Optional<InputItem> cityReturned = this.inputItemSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InputItem> createItem(@Valid @RequestBody InputItem item) {
        InputItem inputItem = this.inputItemSerivce.save(item);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(inputItem.getCodigoItemEntrada()).toUri();
        return ResponseEntity.created(uri).body(inputItem);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long code) {
        this.inputItemSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("input-item.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<InputItem> editItem(@Valid @RequestBody InputItem inputItem) {
        InputItem itemReturned = this.inputItemSerivce.edit(inputItem);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("input-item.edited.", String.valueOf(itemReturned.getCodigoItemEntrada()))).build();
    }
}
