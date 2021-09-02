package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Saida;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.SaidaSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/saida")
public class OutputController {

    @Autowired
    private SaidaSerivce saidaSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<Saida> findByCode(@PathVariable Long code) {
        Optional<Saida> exitReturned = this.saidaSerivce.findByCode(code);
        return exitReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Saida> createExit(@Valid @RequestBody Saida saida) {
        Saida exitCreate = this.saidaSerivce.save(saida);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(exitCreate.getCodigoSaida()).toUri();
        return ResponseEntity.created(uri).body(exitCreate);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteExit(@PathVariable Long code) {
        this.saidaSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("saida.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Saida> editExit(@Valid @RequestBody Saida saida) {
        Saida exitReturned = this.saidaSerivce.edit(saida);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("saida editada.", String.valueOf(exitReturned.getCodigoSaida()))).build();
    }
}
