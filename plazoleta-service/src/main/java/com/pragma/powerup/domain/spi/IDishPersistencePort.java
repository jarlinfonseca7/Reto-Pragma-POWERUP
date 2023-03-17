package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {
    DishModel saveDish(DishModel dishModel);

    DishModel getDishById(Long id);

    List<DishModel> getAllDishes();

    void deleteDishById(Long id);
}
