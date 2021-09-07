package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.domain.Provider;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "provider")
public interface ProviderControllerOpenApi {

    @ApiOperation("search provider by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "provider id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "provider not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("provider")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Provider> findByCode(
            @ApiParam(value = "provider id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a provider")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered provider"),
    })
    @PostMapping
    @CacheEvict("provider")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Provider> createProvider(
            @ApiParam(name = "body", value = "Representation of a new provider", required = true)
                    Provider provider);

    @ApiOperation("delete a provider by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded provider"),
            @ApiResponse(code = 404, message = "provider not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("provider")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Void> deleteProvider(
            @ApiParam(value = "provider id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a provider by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated provider"),
            @ApiResponse(code = 404, message = "provider not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("provider")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Provider> editProvider(
            @ApiParam(name = "body", value = "Representation of a provider with new data", required = true)
                    Provider provider);
}
