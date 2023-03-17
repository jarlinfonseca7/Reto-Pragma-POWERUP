package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

    @Mapping(target = "restaurantModel.id", source = "restaurantModel")
    @Mapping(target = "categoryModel.id", source = "categoryModel")
    DishModel toDish(DishRequestDto dishRequestDto);


    @Mapping(target = "restaurantModel", source = "restaurantModel.id")
    @Mapping(target = "categoryModel", source = "categoryModel.id")
    DishRequestDto toRequest(DishModel dishModel);
}
