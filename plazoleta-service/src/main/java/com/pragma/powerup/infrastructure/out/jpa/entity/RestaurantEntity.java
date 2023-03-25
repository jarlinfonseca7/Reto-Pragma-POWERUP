package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "restaurantes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "resturante_id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[0-9a-zA-Z ]+$", message = "El nombre puede contener números con letras pero no solo números")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El nit es requerido")
    @Pattern(regexp = "\\d+", message = "El nit debe ser númerico")
    @Column(name = "nit", unique = true, nullable = false)
    private String nit;
    @NotBlank(message = "La direccion es requerida")
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotBlank(message = "El telefono es requerido")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "El telefono debe contener máximo 13 caracteres y puede contener el símbolo '+' al inicio")
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NotBlank(message = "La urlLogo es requerida")
    @Column(name = "urlLogo", nullable = false)
    private String urlLogo;
    @NotNull(message = "El id_propietario no puede ser nulo")
    @Min(value = 1, message = "El id_propietario debe ser mayor a cero")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Column(name = "id_propietario", nullable = false)
    private Long idPropietario;
  /*@OneToMany(fetch = FetchType.EAGER,mappedBy = "restauranteId")
    private List<DishEntity> platos;*/
}
