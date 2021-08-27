package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Cidade;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.CidadeSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeSerivce cidadeSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<Cidade> findByCode(@PathVariable Long code) {
        Optional<Cidade> cityReturned = this.cidadeSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cidade> createCity(@Valid @RequestBody Cidade cidade) {
        Cidade city = this.cidadeSerivce.save(cidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(city.getCodigoCidade()).toUri();
        return ResponseEntity.created(uri).body(city);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long code) {
        this.cidadeSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("cidade.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Cidade> editCity(@Valid @RequestBody Cidade cidade) {
        Cidade cityReturned = this.cidadeSerivce.edit(cidade);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("cidade editada.", String.valueOf(cityReturned.getCodigoCidade()))).build();
    }
}
