package com.pragma.powerup.infrastructure.out.jpa.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "platos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "plato_id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El precio es requerido")
    @Column(name = "precio", nullable = false)
    private String precio;
    @NotBlank(message = "La descripcion es requerida")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotBlank(message = "La urlImagen es requerida")
    @Column(name = "url_imagen", nullable = false)
    private String urlImagen;

    @NotBlank(message = "El activo es requerido (true(1) o false(0))")
    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name="restaurant_id")
    private RestaurantEntity restaurantEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name="category_id")
    private CategoryEntity categoryEntity;
}
