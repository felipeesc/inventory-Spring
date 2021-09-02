package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Produto;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.ProductSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductSerivce productSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<Produto> findByCode(@PathVariable Long code) {
        Optional<Produto> cityReturned = this.productSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> createProduct(@Valid @RequestBody Produto produto) {
        Produto product = this.productSerivce.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(product.getCodigoProduto()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long code) {
        this.productSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("produto.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Produto> editProduct(@Valid @RequestBody Produto produto) {
        Produto productReturned = this.productSerivce.edit(produto);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("produto editada.", String.valueOf(productReturned.getCodigoProduto()))).build();
    }
}
