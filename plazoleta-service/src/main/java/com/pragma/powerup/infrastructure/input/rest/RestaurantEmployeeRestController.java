package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.handler.IRestaurantEmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurantEmployee")
@RequiredArgsConstructor
public class RestaurantEmployeeRestController {
    private  final IRestaurantEmployeeHandler restaurantEmployeeHandler;

    @Operation(summary = "Add a new restaurant_employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant_employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant_employee already exists", content = @Content)
    })
    @PostMapping("/")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> saveRestaurantEmployee(@Valid @RequestBody RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        restaurantEmployeeHandler.saveRestaurantEmployee(restaurantEmployeeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
