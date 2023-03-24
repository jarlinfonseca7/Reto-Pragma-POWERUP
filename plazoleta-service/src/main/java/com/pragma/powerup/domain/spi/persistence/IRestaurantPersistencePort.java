package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    RestaurantModel getRestaurantById(Long id);

    List<RestaurantModel> getAllRestaurants();

    void deleteRestaurantById(Long id);
}
