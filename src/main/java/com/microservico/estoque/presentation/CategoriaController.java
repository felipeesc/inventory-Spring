package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Categoria;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.CategoriaSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaSerivce categoriaSerivce;

    @GetMapping("/{code}")
    public ResponseEntity<Categoria> findByCode(@PathVariable Long code) {
        Optional<Categoria> categoryReturned = this.categoriaSerivce.findByCode(code);
        return categoryReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategory(@Valid @RequestBody Categoria categoria) {
        Categoria category = this.categoriaSerivce.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(category.getCodigoCategoria()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long code) {
        this.categoriaSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("categoria.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Categoria> editCategory(@Valid @RequestBody Categoria categoria) {
        Categoria categoryReturned = this.categoriaSerivce.edit(categoria);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Categoria editada.", String.valueOf(categoryReturned.getCodigoCategoria()))).build();
    }
}
