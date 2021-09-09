package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.configuration.oauth.CheckSecurity;
import com.microservico.estoque.domain.Transport;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "transport")
public interface TransportControllerOpenApi {

    @ApiOperation("search transport by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "transport id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "transport not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("transport")
    @CheckSecurity.Transport.CanConsult
    ResponseEntity<Transport> findByCode(
            @ApiParam(value = "transport id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a transport")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered transport"),
    })
    @PostMapping
//    @CacheEvict("transport")
    @CheckSecurity.Transport.CanRegister
    ResponseEntity<Transport> createTransport(
            @ApiParam(name = "body", value = "Representation of a new transport", required = true)
                    Transport transport);

    @ApiOperation("delete a transport by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded transport"),
            @ApiResponse(code = 404, message = "transport not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("transport")
    @CheckSecurity.Transport.CanRemove
    ResponseEntity<Void> deleteTransport(
            @ApiParam(value = "transport id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a transport by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated transport"),
            @ApiResponse(code = 404, message = "transport not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("transport")
    @CheckSecurity.Transport.CanEdit
    ResponseEntity<Transport> editTransport(
            @ApiParam(name = "body", value = "Representation of a transport with new data", required = true)
                    Transport transport);
}
