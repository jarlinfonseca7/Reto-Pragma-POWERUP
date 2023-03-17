package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantEntityMapper {
    @Mapping(target = "dishEntityList", source = "dishModelList")
    RestaurantEntity toEntity(RestaurantModel restaurantModel);
    @Mapping(target = "dishModelList", source = "dishEntityList")
    RestaurantModel toRestaurantModel(RestaurantEntity restaurantEntity);
    List<RestaurantModel> toRestaurantModelList(List<RestaurantEntity> restaurantModelList);

}
