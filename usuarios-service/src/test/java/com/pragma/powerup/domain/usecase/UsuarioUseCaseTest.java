package com.pragma.powerup.domain.usecase;


import com.pragma.powerup.domain.model.Usuario;
import com.pragma.powerup.domain.spi.persistence.IUsuarioPersistencePort;
import com.pragma.powerup.factory.FactoryUsersDataTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class UsuarioUseCaseTest {

    @InjectMocks
    UsuarioUseCase usuarioUseCase;

    @Mock
    IUsuarioPersistencePort usuarioPersistencePort;


    @Test
    void mustSaveAUser() {
        Usuario usuario= FactoryUsersDataTest.getUsuario();

        usuarioUseCase.saveUser(usuario);

        //Then
        Mockito.verify(usuarioPersistencePort).saveUser(Mockito.any(Usuario.class));
    }

    @Test()
    void mustSaveAUserAndValidatePasswordBcrypt(){
        Usuario usuario= FactoryUsersDataTest.getUsuario();

        usuarioUseCase.saveUser(usuario);

        //Then
        Mockito.verify(usuarioPersistencePort).saveUser(Mockito.any(Usuario.class));

        assertNotNull(usuario.getClave());
        //La clave ha sido encriptada no sera igual
        assertNotEquals(usuario.getClave(), "password");

    }

    @Test
    void mustGetAUserById() {
        Usuario usuario= FactoryUsersDataTest.getUsuario();

        Mockito.when(usuarioPersistencePort.getUserById(Mockito.anyLong())).thenReturn(usuario);

        usuarioUseCase.getUserById(usuario.getId());
        Mockito.verify(usuarioPersistencePort).getUserById(Mockito.anyLong());

    }

}