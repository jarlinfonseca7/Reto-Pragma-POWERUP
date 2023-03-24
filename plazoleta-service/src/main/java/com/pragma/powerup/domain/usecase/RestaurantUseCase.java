package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.feignclients.IUserFeignClientPort;
import com.pragma.powerup.infrastructure.out.feignclients.dto.UserDto;
import com.pragma.powerup.infrastructure.exception.UserMustBeOwnerException;
import com.pragma.powerup.infrastructure.exception.UserNotExistException;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private  final IRestaurantPersistencePort restaurantPersistencePort;

    private  final IUserFeignClientPort userFeignClient;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserFeignClientPort userFeignClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        boolean existUser = userFeignClient.existsUserById(restaurantModel.getId_propietario());
        System.out.println(existUser);

        if (!existUser) throw new UserNotExistException();
        UserModel user = userFeignClient.getUserById(restaurantModel.getId_propietario());
        System.out.println(user.getRol());
        if (user.getRol().getId() != 2) throw new UserMustBeOwnerException();


        System.out.println("Es un propietario");
        restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public RestaurantModel getRestaurantById(Long id) {
        return restaurantPersistencePort.getRestaurantById(id);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public void deleteRestaurantById(Long id) {
         restaurantPersistencePort.deleteRestaurantById(id);
    }
}
