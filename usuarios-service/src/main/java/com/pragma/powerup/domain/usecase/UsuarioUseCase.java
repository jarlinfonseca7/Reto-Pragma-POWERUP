package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUsuarioServicePort;
import com.pragma.powerup.domain.model.Usuario;
import com.pragma.powerup.domain.spi.IUsuarioPersistencePort;

import java.util.List;

public class UsuarioUseCase implements IUsuarioServicePort {
    private final IUsuarioPersistencePort usuarioPersistencePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioPersistencePort.guardarUsuario(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {

        return usuarioPersistencePort.obtenerUsuarioPorId(id);
    }


    @Override
    public List<Usuario> obtenerTodosUsuarios() {

        return usuarioPersistencePort.obtenerTodosUsuarios();
    }

    @Override
    public void eliminarUsuarioPorId(Long id) {
        usuarioPersistencePort.eliminarUsuarioPorId(id);
    }


}
