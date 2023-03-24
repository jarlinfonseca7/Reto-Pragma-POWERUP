package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.impl.DishHandler;
import com.pragma.powerup.application.handler.impl.RestaurantHandler;
import com.pragma.powerup.infrastructure.exception.OwnerAuthMustBeOwnerRestuarant;
import com.pragma.powerup.infrastructure.feignconfiguration.SecurityFeignRequestInterceptor;
import com.pragma.powerup.infrastructure.security.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final DishHandler dishHandler;
    private  final RestaurantHandler restaurantHandler;
    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequestDto dish) {
        dish.setActivo(true);
        String bearerToken = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        System.out.println("Token: "+bearerToken);
        String correo = TokenUtils.getCorreo(bearerToken.replace("Bearer ",""));

        Long idOwnerAuth = TokenUtils.getUsuarioAutenticadoId(bearerToken.replace("Bearer ",""));

        Long idOwnerRestaurant =  restaurantHandler.getRestaurantById(dish.getRestauranteId()).getId_propietario();



        System.out.println("ID del propietario autenticado: "+idOwnerAuth);
        System.out.println("CORREO del propietario: "+correo);
        System.out.println("ID del restaurante del plato: "+dish.getRestauranteId());
        System.out.println("ID del propietario del restaurante: "+ idOwnerRestaurant);
        if(idOwnerAuth!=idOwnerRestaurant) throw new OwnerAuthMustBeOwnerRestuarant();



        dishHandler.saveDish(dish);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishRequestDto> updateDish(@PathVariable(value = "id")Long dishId, @RequestBody DishUpdateRequestDto dishUpdateRequestDto){
        dishHandler.updateDish(dishId, dishUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Operation(summary = "Get all dishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All dishes returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DishResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return ResponseEntity.ok(dishHandler.getAllDishes());
    }

    @Operation(summary = "Get dish by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DishResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Dish no found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDishById(@PathVariable(value = "id") Long dishId) {
        return ResponseEntity.ok(dishHandler.getDishById(dishId));
    }


    @Operation(summary = "Detele a dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dish not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deteteDishById(@PathVariable(value = "id") Long dishId) {
        dishHandler.deleleDishById(dishId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
