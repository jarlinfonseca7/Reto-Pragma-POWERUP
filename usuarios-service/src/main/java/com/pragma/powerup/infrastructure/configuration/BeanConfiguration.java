package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.mapper.IRestaurantEmployeeRequestMapper;
import com.pragma.powerup.application.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.domain.api.IObjectServicePort;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.api.IUsuarioServicePort;
import com.pragma.powerup.domain.spi.feignclients.IRestaurantEmployeeFeignClientPort;
import com.pragma.powerup.domain.spi.feignclients.IRestaurantFeingClientPort;
import com.pragma.powerup.domain.spi.passwordencoder.IUsuarioPasswordEncoderPort;
import com.pragma.powerup.domain.spi.persistence.IObjectPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRolPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IUsuarioPersistencePort;
import com.pragma.powerup.domain.spi.token.IToken;
import com.pragma.powerup.domain.usecase.ObjectUseCase;
import com.pragma.powerup.domain.usecase.RolUseCase;
import com.pragma.powerup.domain.usecase.UsuarioUseCase;
import com.pragma.powerup.infrastructure.out.feignclients.RestaurantEmployeeFeignClient;
import com.pragma.powerup.infrastructure.out.feignclients.RestaurantFeignClient;
import com.pragma.powerup.infrastructure.out.feignclients.adapter.RestaurantEmployeeFeignAdapter;
import com.pragma.powerup.infrastructure.out.feignclients.adapter.RestaurantFeignAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RolJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UsuarioJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUsuarioEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IObjectRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRolRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUsuarioRepository;
import com.pragma.powerup.infrastructure.out.passwordencoder.BCrypPasswordEncoderAdapter;
import com.pragma.powerup.infrastructure.out.token.TokenAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;

    private  final IUsuarioRepository usuarioRepository;
    private  final IUsuarioEntityMapper usuarioEntityMapper;

    private final IRolRepository rolRepository;

    private final IRolEntityMapper rolEntityMapper;

    private final RestaurantEmployeeFeignClient restaurantEmployeeFeignClient;

    private final IRestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;

    private final RestaurantFeignClient restaurantFeignClient;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Bean
    public IObjectPersistencePort objectPersistencePort() {
        return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IObjectServicePort objectServicePort() {

        return new ObjectUseCase(objectPersistencePort());
    }

    @Bean
    public IUsuarioPasswordEncoderPort  usuarioPasswordEncoderPort(){
        return new BCrypPasswordEncoderAdapter();
    }

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort(){
        return new UsuarioJpaAdapter(usuarioRepository, usuarioEntityMapper);
    }

    @Bean
    public IUsuarioServicePort usuarioServicePort(){
        return new UsuarioUseCase(usuarioPersistencePort(), usuarioPasswordEncoderPort(), restaurantEmployeeFeignClientPort(), token(), restaurantFeingClientPort());
    }

    @Bean
    public IRolPersistencePort rolPersistencePort(){
        return new RolJpaAdapter(rolRepository, rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort(){
        return  new RolUseCase(rolPersistencePort());
    }

    @Bean
    public IRestaurantEmployeeFeignClientPort restaurantEmployeeFeignClientPort(){
        return new RestaurantEmployeeFeignAdapter(restaurantEmployeeFeignClient, restaurantEmployeeRequestMapper);
    }

    @Bean
    public IRestaurantFeingClientPort restaurantFeingClientPort(){
        return new RestaurantFeignAdapter(restaurantFeignClient, restaurantResponseMapper);
    }
    @Bean
    public IToken token(){
        return new TokenAdapter();
    }


}