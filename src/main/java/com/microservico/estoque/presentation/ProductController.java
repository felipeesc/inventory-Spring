package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Product;
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
    public ResponseEntity<Product> findByCode(@PathVariable Long code) {
        Optional<Product> cityReturned = this.productSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product produto) {
        Product product = this.productSerivce.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(product.getCodigoProduto()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long code) {
        this.productSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("product.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Product> editProduct(@Valid @RequestBody Product product) {
        Product productReturned = this.productSerivce.edit(product);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("product.edited.", String.valueOf(productReturned.getCodigoProduto()))).build();
    }
}
