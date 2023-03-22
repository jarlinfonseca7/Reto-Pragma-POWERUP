package com.pragma.powerup.factory;

import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.model.Usuario;
import org.apache.catalina.User;

public class FactoryUsersDataTest {

    public static Usuario getUsuario(){
        Usuario usuario= new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Jarlin");
        usuario.setApellido("Fonseca");
        usuario.setCelular("+573238123367");
        usuario.setDocumentoDeIdentidad("1006287478");
        usuario.setCorreo("jarlin@gmail.com");
        usuario.setClave("password");
        usuario.setRol(getRol());
        return usuario;
    }

    public static Rol getRol(){
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNombre("ADMIN");
        rol.setDescripcion("Rol de Admin");
        return  rol;
    }
}
