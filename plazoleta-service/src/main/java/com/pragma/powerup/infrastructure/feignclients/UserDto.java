package com.pragma.powerup.infrastructure.feignclients;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String documentoDeIdentidad;
    private String celular;
    private String correo;
    private String clave;
    private RolDto rol;
    //private Long rol;

}
