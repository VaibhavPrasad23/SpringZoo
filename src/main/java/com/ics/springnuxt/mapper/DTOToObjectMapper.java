package com.ics.springnuxt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.ics.springnuxt.dto.CreateCarDto;
import com.ics.springnuxt.dto.CreateUserDto;
import com.ics.springnuxt.dto.UpdateCarDto;
import com.ics.springnuxt.entity.Car;
import com.ics.springnuxt.entity.UsedCar;
import com.ics.springnuxt.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DTOToObjectMapper 
{
	Car car(CreateCarDto dto);
	
	User user(CreateUserDto UserDTO);
	
	@Mapping(target = "id", ignore = true)
	UsedCar usedCar(CreateCarDto UsedCarDTO);

	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "name", source = "name")
    UsedCar updateCarToUsedCar(Car dto);	

	@Mapping(target = "fuel", ignore = true)
	UsedCar updateUsedCar(UpdateCarDto updateCarDTO);	
	
	
    void updateCarFromDto(UpdateCarDto dto, @MappingTarget UsedCar entity);

    void updateCarFromDto(UpdateCarDto dto, @MappingTarget Car entity);
    
    void updateCarFromUsedCarDto(UpdateCarDto dto, @MappingTarget Car car);

}
