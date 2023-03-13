package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UsuarioRequestDto;
import com.pragma.powerup.application.dto.response.UsuarioResponseDto;
import com.pragma.powerup.domain.model.Usuario;

import java.util.List;

public interface IUsuarioHandler {

    void guardarUsuario(UsuarioRequestDto usuarioRequestDto);

     UsuarioRequestDto obtenerUsuarioPorId(Long id);

    List<UsuarioResponseDto> obtenerTodosUsuarios();

     void eliminarUsuarioPorId(Long id);

}
