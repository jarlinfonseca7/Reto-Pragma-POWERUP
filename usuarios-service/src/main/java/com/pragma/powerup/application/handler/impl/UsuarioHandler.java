package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UsuarioRequestDto;
import com.pragma.powerup.application.dto.response.UsuarioResponseDto;
import com.pragma.powerup.application.handler.IUsuarioHandler;
import com.pragma.powerup.application.mapper.IUsuarioRequestMapper;
import com.pragma.powerup.application.mapper.IUsuarioResponseMapper;
import com.pragma.powerup.domain.api.IUsuarioServicePort;
import com.pragma.powerup.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioHandler implements IUsuarioHandler {

    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioRequestMapper usuarioRequestMapper;
    private final IUsuarioResponseMapper usuarioResponseMapper;

    @Override
    public void guardarUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRequestMapper.toUsuario(usuarioRequestDto);
        usuarioServicePort.guardarUsuario(usuario);

    }


    @Override
    public UsuarioRequestDto obtenerUsuarioPorId(Long id) {
        //UsuarioResponseDto usuario = usuarioResponseMapper.toResponse(usuarioServicePort.obtenerUsuarioPorId(id));
        UsuarioRequestDto usuarioRequestDto = usuarioRequestMapper.toRequest(usuarioServicePort.obtenerUsuarioPorId(id));
        return usuarioRequestDto;
    }


    @Override
    public List<UsuarioResponseDto> obtenerTodosUsuarios() {
        return usuarioResponseMapper.toResponseList(usuarioServicePort.obtenerTodosUsuarios());
    }

    @Override
    public void eliminarUsuarioPorId(Long id) {
        usuarioServicePort.eliminarUsuarioPorId(id);
    }


}
