package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UsuarioRequestDto;
import com.pragma.powerup.application.dto.response.UsuarioResponseDto;
import com.pragma.powerup.application.handler.IUsuarioHandler;
import com.pragma.powerup.application.mapper.IUsuarioRequestMapper;
import com.pragma.powerup.application.mapper.IUsuarioResponseMapper;
import com.pragma.powerup.domain.api.IUsuarioServicePort;
import com.pragma.powerup.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UsuarioRequestDto usuarioRequestDto) {
       // usuarioRequestDto.setClave(passwordEncoder.encode(usuarioRequestDto.getClave()));
        Usuario usuario = usuarioRequestMapper.toUsuario(usuarioRequestDto);
        usuarioServicePort.saveUser(usuario);

    }

    @Override
    public UsuarioResponseDto getUserById(Long id) {
        UsuarioResponseDto usuarioResponseDto = usuarioResponseMapper.toResponse(usuarioServicePort.getUserById(id));
        return usuarioResponseDto;
    }

    @Override
    public Boolean existsUserById(Long id) {
        return usuarioServicePort.existsUserById(id);
    }


    @Override
    public List<UsuarioResponseDto> getAllUsers() {
        return usuarioResponseMapper.toResponseList(usuarioServicePort.getAllUsers());
    }

    @Override
    public void deleteUserById(Long id) {
        usuarioServicePort.deleteUserById(id);
    }


}
