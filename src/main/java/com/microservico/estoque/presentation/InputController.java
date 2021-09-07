package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Input;
import com.microservico.estoque.presentation.openapi.InputControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.InputSerivce;
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
@RequestMapping("/entrada")
public class InputController implements InputControllerOpenApi {

    @Autowired
    private InputSerivce inputSerivce;

    @Cacheable("input")
    @GetMapping("/{code}")
    public ResponseEntity<Input> findByCode(@PathVariable Long code) {
        Optional<Input> entryReturned = this.inputSerivce.findByCode(code);
        return entryReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @CacheEvict("input")
    public ResponseEntity<Input> createInput(@Valid @RequestBody Input input) {
        Input inputSave = this.inputSerivce.save(input);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(inputSave.getCodigoEntrada()).toUri();
        return ResponseEntity.created(uri).body(inputSave);
    }

    @PostMapping("/{code}")
    @CacheEvict("input")
    public ResponseEntity<Void> deleteInput(@PathVariable Long code) {
        this.inputSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("input.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    @CacheEvict("input")
    public ResponseEntity<Input> editInput(@Valid @RequestBody Input input) {
        Input entryReturned = this.inputSerivce.edit(input);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("input.edited.", String.valueOf(entryReturned.getCodigoEntrada()))).build();
    }
}
