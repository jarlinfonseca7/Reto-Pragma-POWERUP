package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDto {
    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private  String celular;

    private String correo;
    private String clave;
    private Long rol;


}
