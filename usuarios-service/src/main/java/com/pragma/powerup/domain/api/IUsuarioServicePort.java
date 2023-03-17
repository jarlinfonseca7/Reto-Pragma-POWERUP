package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Usuario;

import java.util.List;

public interface IUsuarioServicePort {

    void saveUser(Usuario usuario);

    Usuario getUserById(Long id);

    Boolean existsUserById(Long id);

    List<Usuario> getAllUsers();

    void deleteUserById(Long id);
}
