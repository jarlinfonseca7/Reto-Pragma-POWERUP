package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;


import java.util.List;

public interface IDishServicePort {

    void saveDish(DishModel dishModel);

    DishModel getDishById(Long id);

    void updateDish(Long id, DishModel dishModel);

    void updateEnableDisableDish(Long idDish, Long flag);

    List<DishModel> getAllDishes();

    void deleteDishById(Long id);
}
