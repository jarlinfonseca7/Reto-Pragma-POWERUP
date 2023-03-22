package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class DishUseCaseTest {

    @InjectMocks
    DishUseCase dishUseCase;

    @Mock
    IDishPersistencePort dishPersistencePort;

    @Test
    void mustSaveADish() {
        RestaurantModel restaurantModel= new RestaurantModel();
        restaurantModel.setId(1L);
        restaurantModel.setNombre("Frisby");
        restaurantModel.setNit("1654144544");
        restaurantModel.setDireccion("Unicentro");
        restaurantModel.setTelefono("3126544545");
        restaurantModel.setUrlLogo("URL");
        restaurantModel.setId_propietario(1L);

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(1L);
        categoryModel.setNombre("Comidas rapidas");
        categoryModel.setDescripcion("Rapidas");


        DishModel dishModel = new DishModel();
        dishModel.setId(1L);
        dishModel.setNombre("Pollo Frito");
        dishModel.setPrecio("73000");
        dishModel.setDescripcion("Sabroso chicken");
        dishModel.setUrlImagen("URL");
        dishModel.setActivo(true);
        dishModel.setRestauranteId(restaurantModel);
        dishModel.setCategoriaId(categoryModel);

        dishUseCase.saveDish(dishModel);

        Mockito.verify(dishPersistencePort).saveDish(Mockito.any(DishModel.class));



    }

    @Test
    void mustUpdateADish() {
        RestaurantModel restaurantModel= new RestaurantModel();
        restaurantModel.setId(1L);
        restaurantModel.setNombre("Frisby");
        restaurantModel.setNit("1654144544");
        restaurantModel.setDireccion("Unicentro");
        restaurantModel.setTelefono("3126544545");
        restaurantModel.setUrlLogo("URL");
        restaurantModel.setId_propietario(1L);

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(1L);
        categoryModel.setNombre("Comidas rapidas");
        categoryModel.setDescripcion("Rapidas");


        DishModel dishModel = new DishModel();
        dishModel.setId(1L);
        dishModel.setNombre("Pollo Frito");
        dishModel.setPrecio("73000");
        dishModel.setDescripcion("Sabroso chicken");
        dishModel.setUrlImagen("URL");
        dishModel.setActivo(true);
        dishModel.setRestauranteId(restaurantModel);
        dishModel.setCategoriaId(categoryModel);

        DishModel dishUpdate = new DishModel();
        dishUpdate.setPrecio("77000");
        dishUpdate.setDescripcion("Plato con 10 presas de pollo, mas ensalada de repollo y 4 vasos de gasesosa");


        Mockito.when(dishPersistencePort.getDishById(1L)).thenReturn(dishModel);

        dishUseCase.updateDish(1L, dishUpdate);

        Mockito.verify(dishPersistencePort).getDishById(1L);
        Mockito.verify(dishPersistencePort).saveDish(dishModel);

        ArgumentCaptor<DishModel> captor = ArgumentCaptor.forClass(DishModel.class);
        Mockito.verify(dishPersistencePort).saveDish(captor.capture());
        DishModel savedDish = captor.getValue();
        assertEquals("77000", savedDish.getPrecio());
        assertEquals("Plato con 10 presas de pollo, mas ensalada de repollo y 4 vasos de gasesosa", savedDish.getDescripcion());


        assertEquals("77000", dishModel.getPrecio());
        assertEquals("Plato con 10 presas de pollo, mas ensalada de repollo y 4 vasos de gasesosa", dishModel.getDescripcion());

    }
}