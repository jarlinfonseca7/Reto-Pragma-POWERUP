package com.pragma.powerup.infrastructure.out.feignclients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "plazoleta-service", url = "localhost:8082/api/v1/restaurantEmployee")
public interface RestaurantFeignClient {

    void saveRestaurantEmployee();


}
