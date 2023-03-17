package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Usuario;

import java.util.List;

public interface IUsuarioPersistencePort {

    Usuario saveUser(Usuario usuario);

    Usuario getUserById(Long id);

    Boolean existsUserById(Long id);

    List<Usuario> getAllUsers();

    void deleteUserById(Long id);
}
