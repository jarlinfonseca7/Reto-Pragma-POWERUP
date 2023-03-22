package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.DishModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryRequestDto {
    private String nombre;
    private String descripcion;
}
