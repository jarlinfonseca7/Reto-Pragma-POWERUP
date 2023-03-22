package com.pragma.powerup.application.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequestDto {
    private String nombre;
    private String precio;
    private String descripcion;
    private String urlImagen;
    private Boolean activo;
    private Long restauranteId;
    private Long categoriaId;
}
