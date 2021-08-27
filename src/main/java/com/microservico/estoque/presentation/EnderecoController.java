package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Cidade;
import com.microservico.estoque.domain.Endereco;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.EnderecoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoSerivce enderecoSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<Endereco> findByCode(@PathVariable Long code) {
        Optional<Endereco> cityReturned = this.enderecoSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Endereco> createAddress(@Valid @RequestBody Endereco endereco) {
        Endereco enderecoSalvo = this.enderecoSerivce.save(endereco);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(enderecoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoSalvo);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long code) {
        this.enderecoSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("endereço.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Cidade> editAddress(@Valid @RequestBody Endereco endereco) {
        Endereco addressReturned = this.enderecoSerivce.edit(endereco);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("endereço editado.", String.valueOf(addressReturned.getId()))).build();
    }
}
