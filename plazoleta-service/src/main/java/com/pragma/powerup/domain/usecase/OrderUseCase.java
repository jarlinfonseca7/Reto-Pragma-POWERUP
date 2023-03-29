package com.pragma.powerup.domain.usecase;


import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.ClientHasAnOrderException;
import com.pragma.powerup.domain.exception.DishNotExistException;
import com.pragma.powerup.domain.exception.OwnerNotAuthenticatedException;
import com.pragma.powerup.domain.exception.RestaurantNotExistException;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderDishModel;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.orders.OrderDishRequestModel;
import com.pragma.powerup.domain.model.orders.OrderDishResponseModel;
import com.pragma.powerup.domain.model.orders.OrderRequestModel;
import com.pragma.powerup.domain.model.orders.OrderResponseModel;
import com.pragma.powerup.domain.spi.bearertoken.IToken;
import com.pragma.powerup.domain.spi.persistence.IDishPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantEmployeePersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderUseCase implements IOrderServicePort {

    private  final IOrderPersistencePort orderPersistencePort;

    private final IToken token;

    private final IRestaurantPersistencePort restaurantPersistencePort;

    private final IDishPersistencePort dishPersistencePort;

    private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IToken token, IRestaurantPersistencePort restaurantPersistencePort, IDishPersistencePort dishPersistencePort, IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.token = token;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantEmployeePersistencePort = restaurantEmployeePersistencePort;
    }

    @Override
    public void saveOrder(OrderRequestModel orderRequestModel) {


        Date date = new Date();
        String bearerToken = token.getBearerToken();
        if(bearerToken==null) throw new OwnerNotAuthenticatedException();
        Long idClientAuth = token.getUsuarioAutenticadoId(bearerToken);

        List<String> estados = List.of("PENDIENTE", "EN_PREPARACION", "LISTO");

        if(orderPersistencePort.existsByIdClienteAndEstado(idClientAuth, estados.get(0)) ||
           orderPersistencePort.existsByIdClienteAndEstado(idClientAuth, estados.get(1)) ||
           orderPersistencePort.existsByIdClienteAndEstado(idClientAuth, estados.get(2))) throw new ClientHasAnOrderException();

        Long idRestaurante = orderRequestModel.getResturanteId();

        RestaurantModel restaurantModel= restaurantPersistencePort.getRestaurantById(idRestaurante);
        if(restaurantModel==null) throw new RestaurantNotExistException();
        OrderModel orderModel2 = new OrderModel(-1L, idClientAuth,date,"PENDIENTE",null,restaurantModel);

        //orderModel2.setIdCliente(orderModel.get);


         OrderModel order =orderPersistencePort.saveOrder(orderModel2);

        List<OrderDishRequestModel> orderDishes = orderRequestModel.getPlatos();
        if(orderDishes.isEmpty()){
            throw new NoDataFoundException();
        }
        List<OrderDishModel> orderDishesEmpty = new ArrayList<>();
        for (int i=0; i<orderDishes.size();i++){
            DishModel dishModel= dishPersistencePort.getDishById(orderDishes.get(i).getIdPlatos());
            if(dishModel==null) throw new DishNotExistException();
            if(dishModel.getRestauranteId().getId() ==  order.getRestaurante().getId()){
                OrderDishModel orderDish = new OrderDishModel(-1L, order,dishModel, String.valueOf(orderDishes.get(i).getCantidad()));
                orderDishesEmpty.add(orderDish);
            }
        }
        orderPersistencePort.saveOrderDish(orderDishesEmpty);
         //orderPersistencePort.saveOrderDish();
    }

    @Override
    public List<com.pragma.powerup.domain.model.orders.OrderResponseModel> getAllOrdersWithPagination(Integer page, Integer size, String estado) {
        String bearerToken = token.getBearerToken();
        if(bearerToken==null) throw new OwnerNotAuthenticatedException();
        Long idEmployeeAuth = token.getUsuarioAutenticadoId(bearerToken);
        RestaurantEmployeeModel restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(String.valueOf(idEmployeeAuth));

        List<OrderResponseModel> listaPedidosResponse = new ArrayList<>();
        Long restauranteId = Long.parseLong(restaurantEmployeeModel.getRestaurantId());
        List<OrderModel> pedidos = orderPersistencePort.getAllOrdersWithPagination(page, size,restauranteId ,estado);

        for (int i=0; i<pedidos.size();i++){
            OrderResponseModel orderResponseModel = new OrderResponseModel();
            orderResponseModel.setId(pedidos.get(i).getId());
            orderResponseModel.setIdCliente(pedidos.get(i).getIdCliente());
            if(pedidos.get(i).getChef()==null) orderResponseModel.setIdChef(null);
            else orderResponseModel.setIdChef(pedidos.get(i).getChef().getId());
            orderResponseModel.setFecha(pedidos.get(i).getFecha());
            orderResponseModel.setPedidoPlatos(new ArrayList<>());

            List<OrderDishModel>  pedidoPlatos = orderPersistencePort.getAllOrdersByPedido(pedidos.get(i).getId());
            for (int k=0; k<pedidoPlatos.size(); k++){
                DishModel dishModel= dishPersistencePort.getDishById(pedidoPlatos.get(k).getPlato().getId());
                 OrderDishResponseModel orderDishResponseModel = new OrderDishResponseModel();
                 orderDishResponseModel.setId(dishModel.getId());
                 orderDishResponseModel.setNombre(dishModel.getNombre());
                 orderDishResponseModel.setPrecio(dishModel.getPrecio());
                 orderDishResponseModel.setDescripcion(dishModel.getDescripcion());
                 orderDishResponseModel.setUrlImagen(dishModel.getUrlImagen());
                 orderDishResponseModel.setCategoriaId(dishModel.getCategoriaId());
                 orderDishResponseModel.setCantidad(pedidoPlatos.get(k).getCantidad());

                orderResponseModel.getPedidoPlatos().add(orderDishResponseModel);
            }
             listaPedidosResponse.add(orderResponseModel);
        }
         return listaPedidosResponse;
    }

}
