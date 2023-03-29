package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.orders.OrderRequestModel;
import com.pragma.powerup.domain.model.orders.OrderResponseModel;

import java.util.List;


public interface IOrderServicePort {

    void saveOrder(OrderRequestModel orderModel);

    List<OrderResponseModel> getAllOrdersWithPagination(Integer page, Integer size, String estado);

    //void saveOrderDish(OrderRequestModel orderRequestModel);
}
