package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.domain.Product;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "product")
public interface ProductControllerOpenApi {

    @ApiOperation("search product by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "product id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "product not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("product")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Product> findByCode(
            @ApiParam(value = "product id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered product"),
    })
    @PostMapping
    @CacheEvict("product")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Product> createProduct(
            @ApiParam(name = "body", value = "Representation of a new product", required = true)
                    Product product);

    @ApiOperation("delete a product by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded product"),
            @ApiResponse(code = 404, message = "product not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("product")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Void> deleteProduct(
            @ApiParam(value = "product id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a product by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated product"),
            @ApiResponse(code = 404, message = "product not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("product")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Product> editProduct(
            @ApiParam(name = "body", value = "Representation of a product with new data", required = true)
                    Product product);
}
