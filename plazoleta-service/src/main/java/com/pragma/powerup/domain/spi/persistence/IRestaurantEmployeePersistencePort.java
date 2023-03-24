package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;

import java.util.List;

public interface IRestaurantEmployeePersistencePort {
    RestaurantEmployeeModel saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel);

    List<RestaurantEmployeeModel> getAllRestaurantEmployees();
}
