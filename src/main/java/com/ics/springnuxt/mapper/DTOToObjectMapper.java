package com.ics.springnuxt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.ics.springnuxt.dto.CreateCarDto;
import com.ics.springnuxt.dto.UserDto;
import com.ics.springnuxt.entity.Car;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DTOToObjectMapper 
{
	Car car(CreateCarDto dto);
	
	User user(UserDto userDTO);
}
