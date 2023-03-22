package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.handler.IRolHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/v1/rol")
@RequiredArgsConstructor
public class RolRestController {

    private final IRolHandler rolHandler;

    @Operation(summary = "Add a new rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> guardarRol(@RequestBody RolRequestDto rolRequestDto){
        rolHandler.guardarRol(rolRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<RolResponseDto>> obtenerTodosRoles(){
        return ResponseEntity.ok(rolHandler.obtenerTodosRoles());
    }

    // Obtener un usuario por ID

    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDto> getRolById(@PathVariable(value = "id") Long rolId) {
        return  ResponseEntity.ok(rolHandler.obtenerRolPorId(rolId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRolPorId(@PathVariable(value = "id")Long rolId){
        rolHandler.eliminarRolPorId(rolId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
