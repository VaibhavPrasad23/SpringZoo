package com.ics.springnuxt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.ics.springnuxt.dto.CreateCarDto;
import com.ics.springnuxt.dto.UserDto;
import com.ics.springnuxt.entity.Car;
import com.ics.springnuxt.entity.UsedCar;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DTOToObjectMapper 
{
	Car car(CreateCarDto dto);
	
	User user(UserDto userDTO);
	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "name", source = "name")
    UsedCar updateCarToUsedCar(Car dto);	
}
