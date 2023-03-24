package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RestaurantUseCaseTest {

    @InjectMocks
    RestaurantUseCase restaurantUseCase;

    @Mock
    IRestaurantPersistencePort restaurantPersistencePort;

    @Test
    void mustSaveARestaurant() {
        RestaurantModel restaurantModel= new RestaurantModel();

        restaurantModel.setId(1L);
        restaurantModel.setNombre("Frisby");
        restaurantModel.setNit("1654144544");
        restaurantModel.setDireccion("Unicentro");
        restaurantModel.setTelefono("3126544545");
        restaurantModel.setUrlLogo("URL");
        restaurantModel.setId_propietario(1L);

        restaurantUseCase.saveRestaurant(restaurantModel);

        Mockito.verify(restaurantPersistencePort).saveRestaurant(Mockito.any(RestaurantModel.class));
    }
}