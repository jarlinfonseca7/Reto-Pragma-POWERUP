package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto);
    DishResponseDto getDishById(Long id);
    List<DishResponseDto> getAllDishes();
    void deleleDishById(Long id);
}
