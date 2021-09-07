package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Product;
import com.microservico.estoque.presentation.openapi.ProductControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.ProductSerivce;
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
@RequestMapping("/produto")
public class ProductController implements ProductControllerOpenApi {

    @Autowired
    private ProductSerivce productSerivce;

    @Override
    public ResponseEntity<Product> findByCode(@PathVariable Long code) {
        Optional<Product> cityReturned = this.productSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product produto) {
        Product product = this.productSerivce.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(product.getCodigoProduto()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable Long code) {
        this.productSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("product.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<Product> editProduct(@Valid @RequestBody Product product) {
        Product productReturned = this.productSerivce.edit(product);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("product.edited.", String.valueOf(productReturned.getCodigoProduto()))).build();
    }
}
