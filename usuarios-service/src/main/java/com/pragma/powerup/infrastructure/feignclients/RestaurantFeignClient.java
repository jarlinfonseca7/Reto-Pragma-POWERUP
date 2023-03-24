package com.pragma.powerup.infrastructure.feignclients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "plazoleta-service", url = "localhost:8082/api/v1/restaurantr")
public interface RestaurantFeignClient {

    void saveRestarantEmployee();


}
