package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;

import java.util.Date;
import java.util.List;

public class OrderRequestDto {

    //private Long idCliente;
    private Long  resturanteId;
    private List<OrderDishRequestDto> platos;

}
