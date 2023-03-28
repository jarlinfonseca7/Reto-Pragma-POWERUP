package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.OrderModel;

public interface IOrderPersistencePort {
    void saveOrder(OrderModel orderModel);
}
