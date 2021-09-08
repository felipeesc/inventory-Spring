package com.microservico.estoque.presentation.openapi;

import com.microservico.estoque.configuration.oauth.CheckSecurity;
import com.microservico.estoque.domain.City;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import springfox.documentation.annotations.Cacheable;

@Api(tags = "City")
public interface CityControllerOpenApi {

    @ApiOperation("search city by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "city id is invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "city not found", response = Problem.class)
    })
    @GetMapping("/{code}")
    @Cacheable("city")
    @CheckSecurity.City.CanConsult
    ResponseEntity<City> findByCode(
            @ApiParam(value = "city id", example = "1", required = true)
                    Long code);

    @ApiOperation("register a city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "registered city"),
    })
    @PostMapping
    @CacheEvict("city")
    @CheckSecurity.City.CanRegister
    ResponseEntity<City> createCity(
            @ApiParam(name = "body", value = "Representation of a new city", required = true)
                    City city);

    @ApiOperation("delete a category by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "excluded city"),
            @ApiResponse(code = 404, message = "city not found", response = Problem.class)
    })
    @PostMapping("/{code}")
    @CacheEvict("city")
    @CheckSecurity.City.CanRemove
    ResponseEntity<Void> deleteCity(
            @ApiParam(value = "city id", example = "1", required = true)
                    Long code);

    @ApiOperation("update a category by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "updated city"),
            @ApiResponse(code = 404, message = "city not found", response = Problem.class)
    })
    @PutMapping("/{code}")
    @CacheEvict("city")
    @CheckSecurity.City.CanEdit
    ResponseEntity<City> editCity(
            @ApiParam(name = "body", value = "Representation of a city with new data", required = true)
                    City city);
}
