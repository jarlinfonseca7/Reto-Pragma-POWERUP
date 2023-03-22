package com.pragma.powerup;

import com.pragma.powerup.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;
import java.util.Optional;


@EnableFeignClients
@SpringBootApplication
public class Plazoleta implements CommandLineRunner {

	@Autowired
	private IDishRepository dishRepository;

	@Autowired
	private ICategoryRepository categoryRepository;
	public static void main(String[] args) {
		SpringApplication.run(Plazoleta.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<DishEntity> dishEntityList= dishRepository.findAll();
		for (DishEntity valor:dishEntityList) {
			System.out.println(valor.getCategoriaId().getId());
		}
		Optional<DishEntity> dishEntity = dishRepository.findById(2L);
		DishEntity dishEntity1= dishEntity.orElse(null);
		System.out.println(dishEntity1.getCategoriaId());

		Optional<CategoryEntity> categoryEntity = categoryRepository.findById(1L);
		CategoryEntity categoryEntity1 = categoryEntity.orElse(null);
		// Obteniendo ID de la categoria
		System.out.println(categoryEntity1.getId());
		// Obteniendo los platos de la categoria
		//System.out.println(categoryEntity1.getPlatos());




	}
}
