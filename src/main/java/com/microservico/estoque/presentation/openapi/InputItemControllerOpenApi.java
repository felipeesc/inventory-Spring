package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.domain.InputItem;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "InputItem")
public interface InputItemControllerOpenApi {

    @ApiOperation("search Input Item by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Input Item id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "Input Item not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("input-item")
    @PreAuthorize("hasAuthority('CONSULT_INPUT_ITEM')")
    ResponseEntity<InputItem> findByCode(
            @ApiParam(value = "Input Item id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a Input Item")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered Input Item"),
    })
    @PostMapping
    @CacheEvict("input-item")
    @PreAuthorize("hasAuthority('REGISTER_INPUT_ITEM')")
    ResponseEntity<InputItem> createItem(
            @ApiParam(name = "body", value = "Representation of a new Input Item", required = true)
                    InputItem inputItem);

    @ApiOperation("delete a Input Item by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded Input Item"),
            @ApiResponse(code = 404, message = "Input Item not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("input-item")
    @PreAuthorize("hasAuthority('REMOVE_INPUT_ITEM')")
    ResponseEntity<Void> deleteItem(
            @ApiParam(value = "Input Item id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a Input Item by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated Input Item"),
            @ApiResponse(code = 404, message = "Input Item not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("input-item")
    @PreAuthorize("hasAuthority('REGISTER_INPUT_ITEM')")
    ResponseEntity<InputItem> editItem(
            @ApiParam(name = "body", value = "Representation of a Input Item with new data", required = true)
                    InputItem inputItem);
}
