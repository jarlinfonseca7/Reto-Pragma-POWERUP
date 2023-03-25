package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUsuarioServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.model.Usuario;
import com.pragma.powerup.domain.spi.feignclients.IRestaurantEmployeeFeignClientPort;
import com.pragma.powerup.domain.spi.feignclients.IRestaurantFeingClientPort;
import com.pragma.powerup.domain.spi.passwordencoder.IUsuarioPasswordEncoderPort;
import com.pragma.powerup.domain.spi.persistence.IUsuarioPersistencePort;
import com.pragma.powerup.domain.spi.token.IToken;


import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioUseCase implements IUsuarioServicePort {
    private final IUsuarioPersistencePort usuarioPersistencePort;

    private  final IUsuarioPasswordEncoderPort usuarioPasswordEncoderPort;

    private  final IRestaurantEmployeeFeignClientPort restaurantEmployeeFeignClientPort;

    private final IToken token;

    private final IRestaurantFeingClientPort restaurantFeingClientPort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IUsuarioPasswordEncoderPort usuarioPasswordEncoderPort, IRestaurantEmployeeFeignClientPort restaurantEmployeeFeignClientPort, IToken token, IRestaurantFeingClientPort restaurantFeingClientPort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.usuarioPasswordEncoderPort = usuarioPasswordEncoderPort;
        this.restaurantEmployeeFeignClientPort = restaurantEmployeeFeignClientPort;
        this.token = token;
        this.restaurantFeingClientPort = restaurantFeingClientPort;
    }

    @Override
    public void saveUser(Usuario usuario) {
       // RestaurantEmployeeModel restaurantEmployeeModel = new RestaurantEmployeeModel();
        String bearerToken = token.getBearerToken();

        Rol rol = new Rol();
        String rolS =token.getUsuarioAutenticadoRol(bearerToken);
        System.out.println(rolS);

        if(rolS.equals("PROPIETARIO")){
            rol.setId(3L);
            usuario.setRol(rol);

            usuario.setClave(usuarioPasswordEncoderPort.encode(usuario.getClave()));
            // validateAllPropertiesUser(usuario);
            usuarioPersistencePort.saveUser(usuario);



        }


        // Obtener el rol y dependiendo del rol que le haya asignado,

        //usuario.setRol();


    }

    @Override
    public void saveRestaurantEmployee(Usuario usuario) {
        RestaurantEmployeeModel restaurantEmployeeModel = new RestaurantEmployeeModel();
        String bearerToken = token.getBearerToken();
        Long idOwnerAuth = token.getUsuarioAutenticadoId(bearerToken);

        RestaurantModel restaurantModel = restaurantFeingClientPort.getRestaurantByIdPropietario(idOwnerAuth);
        String restaurantId = String.valueOf(restaurantModel.getId());
        System.out.println(restaurantId);
        String employee_id = String.valueOf(usuarioPersistencePort.getUserByCorreo(usuario.getCorreo()).getId());
        System.out.println(employee_id);

        restaurantEmployeeModel.setRestaurantId(restaurantId);
        restaurantEmployeeModel.setPersonId(employee_id);
        restaurantEmployeeFeignClientPort.saveRestaurantEmployee(restaurantEmployeeModel);

    }

    public  void validateRolesAuth(Usuario usuario){
        String bearerToken = token.getBearerToken();
        Rol rol = new Rol();



        if(token.getUsuarioAutenticadoRol(bearerToken)=="ADMIN"){
            rol.setId(2L);
            usuario.setRol(rol);
        }
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
