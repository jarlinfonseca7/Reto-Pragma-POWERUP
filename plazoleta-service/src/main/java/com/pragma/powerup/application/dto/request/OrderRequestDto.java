package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter

public class OrderRequestDto {

    //private Long idCliente;
    private List<OrderDishRequestDto> platos;
    private Long  resturanteId;

}
