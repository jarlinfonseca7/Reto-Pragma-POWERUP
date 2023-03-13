package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Usuario;

import java.util.List;

public interface IUsuarioServicePort {

    void guardarUsuario(Usuario usuario);

    Usuario obtenerUsuarioPorId(Long id);

    List<Usuario> obtenerTodosUsuarios();

    void eliminarUsuarioPorId(Long id);
}
