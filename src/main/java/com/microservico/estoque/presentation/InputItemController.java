package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.InputItem;
import com.microservico.estoque.presentation.openapi.InputItemControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.InputItemSerivce;
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
@RequestMapping("/item-entrada")
public class InputItemController implements InputItemControllerOpenApi {

    @Autowired
    private InputItemSerivce inputItemSerivce;

    @Override
    public ResponseEntity<InputItem> findByCode(@PathVariable Long code) {
        Optional<InputItem> cityReturned = this.inputItemSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<InputItem> createItem(@Valid @RequestBody InputItem item) {
        InputItem inputItem = this.inputItemSerivce.save(item);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(inputItem.getCodigoItemEntrada()).toUri();
        return ResponseEntity.created(uri).body(inputItem);
    }

    @Override
    public ResponseEntity<Void> deleteItem(@PathVariable Long code) {
        this.inputItemSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("input-item.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<InputItem> editItem(@Valid @RequestBody InputItem inputItem) {
        InputItem itemReturned = this.inputItemSerivce.edit(inputItem);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("input-item.edited.", String.valueOf(itemReturned.getCodigoItemEntrada()))).build();
    }
}
