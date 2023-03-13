package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UsuarioRequestDto;
import com.pragma.powerup.application.dto.response.UsuarioResponseDto;
import com.pragma.powerup.application.handler.IUsuarioHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final IUsuarioHandler usuarioHandler;

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> guardarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto){
        usuarioHandler.guardarUsuario(usuarioRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioResponseDto>> obtenerTodosUsuarios(){
        return ResponseEntity.ok(usuarioHandler.obtenerTodosUsuarios());
    }

    // Obtener un usuario por ID

   @GetMapping("/{id}")
    public ResponseEntity<UsuarioRequestDto> getUsuarioById(@PathVariable(value = "id") Long usuarioId) {
       System.out.println(usuarioId);
        return  ResponseEntity.ok(usuarioHandler.obtenerUsuarioPorId(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable(value = "id")Long usuarioId){
        usuarioHandler.eliminarUsuarioPorId(usuarioId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
