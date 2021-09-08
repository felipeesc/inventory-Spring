package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.domain.Input;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "Input")
public interface InputControllerOpenApi {

    @ApiOperation("search Input by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Input id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "Input not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("input")
    @PreAuthorize("hasAuthority('CONSULT_INPUT')")
    ResponseEntity<Input> findByCode(
            @ApiParam(value = "Input id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a Input")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered Input"),
    })
    @PostMapping
    @CacheEvict("input")
    @PreAuthorize("hasAuthority('REGISTER_INPUT')")
    ResponseEntity<Input> createInput(
            @ApiParam(name = "body", value = "Representation of a new Input", required = true)
                    Input input);

    @ApiOperation("delete a Input by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded Input"),
            @ApiResponse(code = 404, message = "Input not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("input")
    @PreAuthorize("hasAuthority('REMOVE_INPUT')")
    ResponseEntity<Void> deleteInput(
            @ApiParam(value = "Input id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a Input by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated Input"),
            @ApiResponse(code = 404, message = "Input not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("input")
    @PreAuthorize("hasAuthority('EDIT_INPUT')")
    ResponseEntity<Input> editInput(
            @ApiParam(name = "body", value = "Representation of a Input with new data", required = true)
                    Input input);
}
