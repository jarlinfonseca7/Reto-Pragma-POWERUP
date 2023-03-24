package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.bearertoken.IToken;
import com.pragma.powerup.domain.spi.persistence.IDishPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.OwnerAuthMustBeOwnerRestuarant;



import java.util.List;


public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
     private  final IRestaurantPersistencePort restaurantPersistencePort;

    private  final IToken token;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, IToken token) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort= restaurantPersistencePort;
        this.token = token;
    }

    @Override
    public void saveDish(DishModel dishModel) {
        dishModel.setActivo(true);
        String bearerToken = token.getBearerToken();
        System.out.println("Token: "+bearerToken);
        String correo = token.getCorreo(bearerToken);


        Long idOwnerAuth = token.getUsuarioAutenticadoId(bearerToken);


         Long idOwnerRestaurant =  restaurantPersistencePort.getRestaurantById(dishModel.getRestauranteId().getId()).getId_propietario();



        System.out.println("ID del propietario autenticado: "+idOwnerAuth);
        System.out.println("CORREO del propietario: "+correo);
         System.out.println("ID del restaurante del plato: "+dishModel.getRestauranteId().getId());
        System.out.println("ID del propietario del restaurante: "+ idOwnerRestaurant);
         if(idOwnerAuth!=idOwnerRestaurant) throw new OwnerAuthMustBeOwnerRestuarant();



        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel getDishById(Long id) {
        return dishPersistencePort.getDishById(id);
    }

    @Override
    public void updateDish(Long id, DishModel dishModel) {
       DishModel dishModel2= dishPersistencePort.getDishById(id);
     //   dishModel2.setNombre(dishModel.getNombre());
        dishModel2.setPrecio(dishModel.getPrecio());
        dishModel2.setDescripcion(dishModel.getDescripcion());
        dishPersistencePort.saveDish(dishModel2);
    }


    @Override
    public List<DishModel> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public void deleteDishById(Long id) {
        dishPersistencePort.deleteDishById(id);
    }
}
