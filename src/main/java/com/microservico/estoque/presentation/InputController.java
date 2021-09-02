package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Entrada;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.InputSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/entrada")
public class InputController {

    @Autowired
    private InputSerivce inputSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<Entrada> findByCode(@PathVariable Long code) {
        Optional<Entrada> entryReturned = this.inputSerivce.findByCode(code);
        return entryReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Entrada> createCity(@Valid @RequestBody Entrada entrada) {
        Entrada entradaSave = this.inputSerivce.save(entrada);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(entradaSave.getCodigoEntrada()).toUri();
        return ResponseEntity.created(uri).body(entradaSave);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long code) {
        this.inputSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("entrada.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Entrada> editCity(@Valid @RequestBody Entrada entrada) {
        Entrada entryReturned = this.inputSerivce.edit(entrada);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("entrada editada.", String.valueOf(entryReturned.getCodigoEntrada()))).build();
    }
}
