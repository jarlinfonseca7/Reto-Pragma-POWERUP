package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;


import java.util.List;


public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void saveDish(DishModel dishModel) {
        this.dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel getDishById(Long id) {
        return this.dishPersistencePort.getDishById(id);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return this.dishPersistencePort.getAllDishes();
    }

    @Override
    public void deleteDishById(Long id) {
        this.dishPersistencePort.deleteDishById(id);
    }
}
