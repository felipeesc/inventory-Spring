package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Category;
import com.microservico.estoque.presentation.openapi.CategoryControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.CategorySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoryController implements CategoryControllerOpenApi {

    @Autowired
    private CategorySerivce categorySerivce;

    @Override
    public ResponseEntity<Category> findByCode(@PathVariable Long code) {
        Optional<Category> categoryReturned = this.categorySerivce.findByCode(code);
        return categoryReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category categoria) {
        Category category = this.categorySerivce.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(category.getCodigoCategoria()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @Override
    public ResponseEntity<Void> deleteCategory(@PathVariable Long code) {
        this.categorySerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("category.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<Category> editCategory(@Valid @RequestBody Category category) {
        Category categoryReturned = this.categorySerivce.edit(category);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("category.edited.", String.valueOf(categoryReturned.getCodigoCategoria()))).build();
    }
}
