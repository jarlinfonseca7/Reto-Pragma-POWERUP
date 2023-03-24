package com.pragma.powerup.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pragma.powerup.domain.model.Rol;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UsuarioRequestDto {
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    @NotBlank(message = "El apellido es requerido")
    private String apellido;
    //@Min(value = 1, message = "El campo documento de identidad debe ser mayor que cero")
    //@NumberFormat(style = NumberFormat.Style.NUMBER)
    @NotBlank(message = "El  documentoDeIdentidad es requerido")
    @Pattern(regexp = "\\d+", message = "El documentoDeIdentidad debe ser númerico")
    private String documentoDeIdentidad;
    @NotBlank(message = "El celular es requerido")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "El número de celular debe contener máximo 13 caracteres y puede contener el símbolo '+' al inicio")
    private  String celular;
    @NotBlank(message = "El correo electrónico es requerido")
    @Email(message = "El correo electrónico debe ser válido")
    private String correo;
    @NotBlank(message = "La clave es requerida")
    private String clave;
    //@NotNull(message = "El rol es requerido")
    //@Min(value = 1, message = "El campo rol debe ser mayor que cero")

   //@NotBlank(message = "El rol es requerido")
    private Long rol;


}
