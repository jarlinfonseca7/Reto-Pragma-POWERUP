package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UsuarioRequestDto;
import com.pragma.powerup.application.dto.response.UsuarioResponseDto;
import com.pragma.powerup.domain.model.Usuario;

import java.util.List;

public interface IUsuarioHandler {

    void saveUser(UsuarioRequestDto usuarioRequestDto);

     UsuarioResponseDto getUserById(Long id);

    Boolean existsUserById(Long id);

    List<UsuarioResponseDto> getAllUsers();

     void deleteUserById(Long id);

}
