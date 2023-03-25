package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;


import java.util.List;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    RestaurantResponseDto getRestaurantById(Long id);
    RestaurantResponseDto getRestaurantByIdPropietario(Long id_propietario);

    List<RestaurantResponseDto> getAllRestaurants();

    void deleteRestaurantById(Long id);
}
