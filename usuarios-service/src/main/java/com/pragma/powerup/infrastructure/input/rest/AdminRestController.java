package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UsuarioRequestDto;
import com.pragma.powerup.application.handler.IUsuarioHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminRestController {

    private final IUsuarioHandler usuarioHandler;

    @PostMapping("/crearPropietario")
    public ResponseEntity<Void> crearPropietario(@RequestBody UsuarioRequestDto propietario) {
        // validar que el usuario autenticado sea un administrador


        // guardar el propietario en la base de datos y asignarle el rol de Propietario
        propietario.setRol(2L);
        usuarioHandler.guardarUsuario(propietario);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
