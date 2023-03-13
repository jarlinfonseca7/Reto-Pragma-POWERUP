package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
public class RolRequestDto {

    private String nombre;
    private  String descripcion;
    //private List<Usuario> usuarios;
}
