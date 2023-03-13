package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Usuario;

import java.util.List;

public interface IUsuarioPersistencePort {

    Usuario guardarUsuario(Usuario usuario);

    Usuario obtenerUsuarioPorId(Long id);

    List<Usuario> obtenerTodosUsuarios();

    void eliminarUsuarioPorId(Long id);
}
