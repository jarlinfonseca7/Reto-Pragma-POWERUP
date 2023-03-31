package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantEmployeeRequestDto {

    @NotBlank(message = "El restaurante_id es requerido")
    private String restaurantId;
    @NotBlank(message = "El empleado_id es requerido")
    private String personId;
}
