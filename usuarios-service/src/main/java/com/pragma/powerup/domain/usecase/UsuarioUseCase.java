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
    public void saveUser(Usuario usuario) {
        usuarioPersistencePort.saveUser(usuario);
    }

    @Override
    public Usuario getUserById(Long id) {

        return usuarioPersistencePort.getUserById(id);
    }

    @Override
    public Boolean existsUserById(Long id) {
        return usuarioPersistencePort.existsUserById(id);
    }


    @Override
    public List<Usuario> getAllUsers() {

        return usuarioPersistencePort.getAllUsers();
    }

    @Override
    public void deleteUserById(Long id) {

        usuarioPersistencePort.deleteUserById(id);
    }


}
