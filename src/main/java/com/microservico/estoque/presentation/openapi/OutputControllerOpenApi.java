package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.domain.Output;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "output")
public interface OutputControllerOpenApi {

    @ApiOperation("search output by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "output id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "output not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("output")
    @PreAuthorize("hasAuthority('CONSULT_OUTPUT')")
    ResponseEntity<Output> findByCode(
            @ApiParam(value = "output id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a output")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered output"),
    })
    @PostMapping
    @CacheEvict("output")
    @PreAuthorize("hasAuthority('REGISTER_OUTPUT')")
    ResponseEntity<Output> createOutput(
            @ApiParam(name = "body", value = "Representation of a new output", required = true)
                    Output output);

    @ApiOperation("delete a output by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded output"),
            @ApiResponse(code = 404, message = "output not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("output")
    @PreAuthorize("hasAuthority('REMOVE_OUTPUT')")
    ResponseEntity<Void> deleteOutput(
            @ApiParam(value = "output id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a output by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated output"),
            @ApiResponse(code = 404, message = "output not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("output")
    @PreAuthorize("hasAuthority('EDIT_OUTPUT')")
    ResponseEntity<Output> editOutput(
            @ApiParam(name = "body", value = "Representation of a output with new data", required = true)
                    Output output);
}
