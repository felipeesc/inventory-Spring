package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.domain.OutputItem;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "OutputItem")
public interface OutputItemControllerOpenApi {

    @ApiOperation("search category by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "category id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "category not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("outputItem")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<OutputItem> findByCode(
            @ApiParam(value = "output item id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a output item")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered output item"),
    })
    @PostMapping
    @CacheEvict("outputItem")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<OutputItem> createItemExit(
            @ApiParam(name = "body", value = "Representation of a new output item", required = true)
                    OutputItem outputItem);

    @ApiOperation("delete a output item by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded output item"),
            @ApiResponse(code = 404, message = "output item not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("outputItem")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<Void> deleteItemExit(
            @ApiParam(value = "output item id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a category by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated output item"),
            @ApiResponse(code = 404, message = "output item not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("outputItem")
    @PreAuthorize("hasAuthority('')")
    ResponseEntity<OutputItem> editItemExit(
            @ApiParam(name = "body", value = "Representation of a output item with new data", required = true)
                    OutputItem outputItem);
}
