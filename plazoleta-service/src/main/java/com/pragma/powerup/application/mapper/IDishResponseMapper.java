package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    @Mapping(target = "restaurantModel", source = "restaurantModel.id")
    @Mapping(target = "categoryModel", source = "categoryModel.id")
    DishResponseDto toResponse(DishModel dishModel);

    List<DishResponseDto> toResponseList(List<DishModel> dishModelList);

}
