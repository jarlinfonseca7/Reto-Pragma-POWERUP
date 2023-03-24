package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RestaurantEmployeeRequestDto {

    @NotBlank(message = "El restaurante_id es requerido")
    private String restaurant_id;

    @NotBlank(message = "El empleado_id es requerido")
    private String person_id;

}
