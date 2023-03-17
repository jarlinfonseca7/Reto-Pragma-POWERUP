package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurantModel);

    RestaurantModel getRestaurantById(Long id);

    List<RestaurantModel> getAllRestaurants();

    void deleteRestaurantById(Long id);
}
