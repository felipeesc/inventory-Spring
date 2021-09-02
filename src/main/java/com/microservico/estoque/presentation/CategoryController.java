package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Categoria;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.CategorySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoryController {

    @Autowired
    private CategorySerivce categorySerivce;

    @GetMapping("/{code}")
    public ResponseEntity<Categoria> findByCode(@PathVariable Long code) {
        Optional<Categoria> categoryReturned = this.categorySerivce.findByCode(code);
        return categoryReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategory(@Valid @RequestBody Categoria categoria) {
        Categoria category = this.categorySerivce.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(category.getCodigoCategoria()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long code) {
        this.categorySerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("categoria.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Categoria> editCategory(@Valid @RequestBody Categoria categoria) {
        Categoria categoryReturned = this.categorySerivce.edit(categoria);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Categoria editada.", String.valueOf(categoryReturned.getCodigoCategoria()))).build();
    }
}
