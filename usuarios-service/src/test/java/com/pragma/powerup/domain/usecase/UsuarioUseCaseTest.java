package com.pragma.powerup.domain.usecase;


import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.model.Usuario;
import com.pragma.powerup.domain.spi.passwordencoder.IUsuarioPasswordEncoderPort;
import com.pragma.powerup.domain.spi.persistence.IUsuarioPersistencePort;
import com.pragma.powerup.domain.spi.token.IToken;
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

    @Mock
    IUsuarioPasswordEncoderPort usuarioPasswordEncoderPort;

    @Mock
    IToken token;


    @Test
    void mustSaveAUserOwner() {
        Usuario usuario= FactoryUsersDataTest.getUsuario();

        Mockito.when(token.getBearerToken()).thenReturn("Bearer token");
        Mockito.when(token.getUsuarioAutenticadoRol("Bearer token")).thenReturn("ADMIN");
        Mockito.when(usuarioPasswordEncoderPort.encode(Mockito.any())).thenReturn("encodedPassword#gfdg23232");

        usuarioUseCase.saveUser(usuario);

        //Then
        Mockito.verify(usuarioPasswordEncoderPort).encode("password");
        Mockito.verify(usuarioPersistencePort).saveUser(Mockito.any(Usuario.class));
    }

    @Test
    void mustSaveAUserEmployee() {
        Usuario usuario= FactoryUsersDataTest.getUsuarioEmployee();

        Mockito.when(token.getBearerToken()).thenReturn("Bearer token");
        Mockito.when(token.getUsuarioAutenticadoRol("Bearer token")).thenReturn("PROPIETARIO");
        Mockito.when(usuarioPasswordEncoderPort.encode(Mockito.any())).thenReturn("encodedPassword#g7hgfh7");

        usuarioUseCase.saveUser(usuario);

        //Then
        Mockito.verify(usuarioPasswordEncoderPort).encode("password");
        Mockito.verify(usuarioPersistencePort).saveUser(Mockito.any(Usuario.class));
    }

    @Test
    void mustSaveAUserClient() {
        Usuario usuario= FactoryUsersDataTest.getUsuarioClient();

        Mockito.when(token.getBearerToken()).thenReturn(null);
       // Mockito.when(usuario.getRol()).thenReturn(null);
        Mockito.when(usuarioPasswordEncoderPort.encode(Mockito.any())).thenReturn("encodedPassword#g7hgfh7");

        usuarioUseCase.saveUser(usuario);

        //Then
        Mockito.verify(usuarioPasswordEncoderPort).encode("password");
        Mockito.verify(usuarioPersistencePort).saveUser(Mockito.any(Usuario.class));
    }

    @Test()
    void mustSaveAUserAndValidatePasswordBcrypt(){
        Usuario usuario= FactoryUsersDataTest.getUsuario();

        Mockito.when(token.getBearerToken()).thenReturn("Bearer token");
        Mockito.when(token.getUsuarioAutenticadoRol("Bearer token")).thenReturn("ADMIN");
        Mockito.when(usuarioPasswordEncoderPort.encode(Mockito.any())).thenReturn("encodedPassword");

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