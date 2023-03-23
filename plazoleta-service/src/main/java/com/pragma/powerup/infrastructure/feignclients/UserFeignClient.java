package com.pragma.powerup.infrastructure.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-service", url = "localhost:8081/api/v1/user")
public interface UserFeignClient {

    @GetMapping("/existsUserById/{id}")
    Boolean existsUserById(@PathVariable(value = "id") Long usuarioId);

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable(value = "id") Long usuarioId);

    @GetMapping("/email/{email}")
    UserDto getUserByCorreo(@PathVariable(value = "email") String correo);
}
