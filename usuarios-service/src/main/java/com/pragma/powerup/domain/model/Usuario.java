package com.pragma.powerup.domain.model;

import com.pragma.powerup.domain.exception.DomainException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Usuario {

    private Long id;
    private String nombre;
    private String apellido;
    private String documentoDeIdentidad;
    private String celular;
    private String correo;
    private String clave;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, String documentoDeIdentidad, String celular, String correo, String clave, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoDeIdentidad = documentoDeIdentidad;
        this.celular = celular;
        this.correo = correo;
        this.clave = clave;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validatePropertyEmptyOrNull(nombre, "nombre");
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        validatePropertyEmptyOrNull(apellido, "apellido");
        this.apellido = apellido;
    }

    public String getDocumentoDeIdentidad() {
        return documentoDeIdentidad;
    }

    public void setDocumentoDeIdentidad(String documentoDeIdentidad) {
        validatePropertyEmptyOrNull(documentoDeIdentidad, "documentoDeIdentidad");
        validateDNI(documentoDeIdentidad, "documentoDeIdentidad");
        this.documentoDeIdentidad = documentoDeIdentidad;
    }

    public String getCelular() {

        return celular;
    }

    public void setCelular(String celular) {
        validatePropertyEmptyOrNull(celular, "celular");
        validateCellPhone(celular, "celular");
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        validatePropertyEmptyOrNull(correo, "correo");
        validateEmail(correo, "correo");
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        validatePropertyEmptyOrNull(clave, "clave");
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void validatePropertyEmptyOrNull(Object property, String description){
        if(property==null) throw new DomainException("El "+description+ " no puede estar nulo");

        if(property instanceof String){
            String propertyS = String.valueOf(property);
                if(propertyS.isEmpty() ) throw new DomainException("El "+description+ " no puede estar vacío");
        }else{
            Long propertyL = (Long)property;
            if(propertyL.toString().isEmpty()) throw  new DomainException("El "+description+ " no puede estar vacío");
        }

    }

    public void validateDNI(String dni, String descripcion){
                if(!dni.matches("\\d+")) throw new DomainException("El "+descripcion+" solo puede ser númerico");
    }
    public void validateEmail(String email, String descripcion) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);
        if(!mather.find()) throw  new DomainException("El " +descripcion+" debe ser válido");
    }

    public void validateCellPhone(String cellphone, String descripcion){
        if(cellphone.length()>13){
            throw  new DomainException("El "+descripcion+" debe contener un máximo de 13 caracteres");
        }
        String regex = "^\\+?\\d{1,12}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellphone);

        if(!matcher.matches()) throw  new DomainException("El " +descripcion+" debe cumplir con máximo 13 caracteres y es " +
                "opcionnal usar al inicio el simbolo +");
    }


}
