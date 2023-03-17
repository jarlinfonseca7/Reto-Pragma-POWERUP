package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;
    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        DishModel dishModel = dishRequestMapper.toDish(dishRequestDto);
        dishServicePort.saveDish(dishModel);
    }

    @Override
    public DishResponseDto getDishById(Long id) {
        DishModel dishModel = dishServicePort.getDishById(id);
        return dishResponseMapper.toResponse(dishModel);
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        List<DishModel> dishModelList = dishServicePort.getAllDishes();
        if(dishModelList.isEmpty()){
            throw  new NoDataFoundException();
        }
        return dishResponseMapper.toResponseList(dishModelList);
    }

    @Override
    public void deleleDishById(Long id) {
        dishServicePort.deleteDishById(id);
    }
}
