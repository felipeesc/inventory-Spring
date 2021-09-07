package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.domain.Category;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "Category")
public interface CategoryControllerOpenApi {

    @ApiOperation("search category by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "category id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "category not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("category")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Category> findByCode(
            @ApiParam(value = "category id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a category")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered category"),
    })
    @PostMapping
    @CacheEvict("category")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Category> createCategory(
            @ApiParam(name = "body", value = "Representation of a new category", required = true)
                    Category category);

    @ApiOperation("delete a category by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded category"),
            @ApiResponse(code = 404, message = "category not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("category")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Void> deleteCategory(
            @ApiParam(value = "category id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a category by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated category"),
            @ApiResponse(code = 404, message = "category not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("category")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Category> editCategory(
            @ApiParam(name = "body", value = "Representation of a category with new data", required = true)
                    Category category);
}
