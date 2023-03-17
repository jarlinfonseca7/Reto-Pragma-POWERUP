package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "usuario_id", nullable = false)
    private Long id;
    @NotBlank(message = "El nombre es requerido")
    @Column(name="nombre", length = 220)
    private String nombre;
    @NotBlank(message = "El apellido es requerido")
    @Column(name="apellido", length = 220)
    private String apellido;

    //@Min(value = 1, message = "El campo documento de identidad debe ser mayor que cero")
    //@NumberFormat(style = NumberFormat.Style.NUMBER)
    @Pattern(regexp = "\\d+", message = "El documento de identidad debe ser númerico")
    @NotBlank(message = "El  documento de identidad es requerido")
    @Column(name="documento_de_identidad",  unique = true)
    private String documentoDeIdentidad;

    @NotBlank(message = "El celular es requerido")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "El número de celular debe contener máximo 13 caracteres y puede contener el símbolo '+' al inicio")
    @Column(name="celular", length = 13)
    private String celular;

    @NotBlank(message = "El correo electrónico es requerido")
    @Email(message = "El correo electrónico debe ser válido")
    @Column(name="correo", unique = true)
    private String correo;

    @NotBlank(message = "La clave es requerida")
    @Column(name="clave")
    private String clave;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rol", nullable = false)
    private RolEntity rol;


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documentoIdentidad=" + documentoDeIdentidad +
                ", celular='" + celular + '\'' +
                ", email='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                ", rol=" + rol +
                '}';
    }
}
