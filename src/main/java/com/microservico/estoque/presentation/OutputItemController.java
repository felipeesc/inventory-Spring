package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.OutputItem;
import com.microservico.estoque.presentation.openapi.OutputItemControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.OutputItemSerivce;
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
@RequestMapping("/item-saida")
public class OutputItemController implements OutputItemControllerOpenApi {

    @Autowired
    private OutputItemSerivce itemSaidaSerivce;

    @Override
    public ResponseEntity<OutputItem> findByCode(@PathVariable Long code) {
        Optional<OutputItem> itemReturned = this.itemSaidaSerivce.findByCode(code);
        return itemReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<OutputItem> createItemExit(@Valid @RequestBody OutputItem item) {
        OutputItem outputItem = this.itemSaidaSerivce.save(item);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(outputItem.getCodigoItemSaida()).toUri();
        return ResponseEntity.created(uri).body(outputItem);
    }

    @Override
    public ResponseEntity<Void> deleteItemExit(@PathVariable Long code) {
        this.itemSaidaSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("output-item.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<OutputItem> editItemExit(@Valid @RequestBody OutputItem outputItem) {
        OutputItem itemReturned = this.itemSaidaSerivce.edit(outputItem);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("output-item.edited.", String.valueOf(itemReturned.getCodigoItemSaida()))).build();
    }
}
