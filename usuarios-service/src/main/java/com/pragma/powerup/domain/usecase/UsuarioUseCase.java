package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUsuarioServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Usuario;
import com.pragma.powerup.domain.spi.passwordencoder.IUsuarioPasswordEncoderPort;
import com.pragma.powerup.domain.spi.persistence.IUsuarioPersistencePort;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioUseCase implements IUsuarioServicePort {
    private final IUsuarioPersistencePort usuarioPersistencePort;

    private  final IUsuarioPasswordEncoderPort usuarioPasswordEncoderPort;


    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IUsuarioPasswordEncoderPort usuarioPasswordEncoderPort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.usuarioPasswordEncoderPort = usuarioPasswordEncoderPort;
    }

    @Override
    public void saveUser(Usuario usuario) {
        // Obtener el rol y dependiendo del rol que le haya asignado,

       //  usuario.setClave(BCrypt.hashpw(usuario.getClave(), BCrypt.gensalt()));
        usuario.setClave(usuarioPasswordEncoderPort.encode(usuario.getClave()));
       // validateAllPropertiesUser(usuario);
        usuarioPersistencePort.saveUser(usuario);
    }

    @Override
    public Usuario getUserById(Long id) {
        return usuarioPersistencePort.getUserById(id);
    }

    @Override
    public Usuario getUserByCorreo(String correo) {
        return usuarioPersistencePort.getUserByCorreo(correo);
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

/*

    public void validateAllPropertiesUser(Usuario usuario) {
        List<String> properties = Arrays.asList("nombre", "apellido", "documentoDeIdentidad", "celular",
                "correo", "password(clave)");

        //Validar que las propiedades no esten nulas o vacias
        validatePropertyEmptyOrNull(usuario.getNombre(), properties.get(0));
        validatePropertyEmptyOrNull(usuario.getApellido(), properties.get(1));
        validatePropertyEmptyOrNull(usuario.getDocumentoDeIdentidad(), properties.get(2));
        validatePropertyEmptyOrNull(usuario.getCelular(), properties.get(3));
        validatePropertyEmptyOrNull(usuario.getCorreo(), properties.get(4));
        validatePropertyEmptyOrNull(usuario.getClave(), properties.get(5));

        //Validar el DNI, celular y correo
        validateDNI(usuario.getDocumentoDeIdentidad());
        validateCellPhone(usuario.getCelular());
        validateEmail(usuario.getCorreo());

    }

    public void validatePropertyEmptyOrNull(Object property, String description) {
        if (property == null) throw new DomainException("El " + description + " no puede estar nulo");

        if (property instanceof String) {
            String propertyS = String.valueOf(property);
            if (propertyS.isEmpty()) throw new DomainException("El " + description + " no puede estar vacío");
        } else {
            Long propertyL = (Long) property;
            if (propertyL.toString().isEmpty())
                throw new DomainException("El " + description + " no puede estar vacío");
        }

    }

    public void validateDNI(String dni) {
        if (!dni.matches("\\d+")) throw new DomainException("El documentoDeIdentidad solo puede ser númerico");
    }

    public void validateEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);
        if (!mather.find()) throw new DomainException("El correo debe ser válido");
    }

    public void validateCellPhone(String cellphone) {
        if (cellphone.length() > 13) {
            throw new DomainException("El celular debe contener un máximo de 13 caracteres");
        }
        String regex = "^\\+?\\d{1,12}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellphone);

        if (!matcher.matches()) throw new DomainException("El celular debe cumplir con máximo 13 caracteres y es " +
                "opcionnal usar al inicio el simbolo +");
    }
*/


}
