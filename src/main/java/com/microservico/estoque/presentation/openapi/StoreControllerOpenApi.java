package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.domain.Store;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "store")
public interface StoreControllerOpenApi {

    @ApiOperation("search store by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "store id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "store not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("store")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Store> findByCode(
            @ApiParam(value = "store id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a store")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered store"),
    })
    @PostMapping
    @CacheEvict("store")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Store> createStore(
            @ApiParam(name = "body", value = "Representation of a new store", required = true)
                    Store store);

    @ApiOperation("delete a store by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded store"),
            @ApiResponse(code = 404, message = "store not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("store")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Void> deleteStore(
            @ApiParam(value = "store id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a output by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated store"),
            @ApiResponse(code = 404, message = "store not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("store")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Store> editStore(
            @ApiParam(name = "body", value = "Representation of a store with new data", required = true)
                    Store store);
}
