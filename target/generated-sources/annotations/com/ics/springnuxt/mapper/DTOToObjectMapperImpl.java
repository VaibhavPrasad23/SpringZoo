package com.ics.springnuxt.mapper;

import com.ics.springnuxt.dto.CreateCarDto;
import com.ics.springnuxt.dto.CreateUserDto;
import com.ics.springnuxt.dto.UpdateCarDto;
import com.ics.springnuxt.entity.Car;
import com.ics.springnuxt.entity.UsedCar;
import com.ics.springnuxt.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-18T19:48:06+0530",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class DTOToObjectMapperImpl implements DTOToObjectMapper {

    @Override
    public Car car(CreateCarDto dto) {
        if ( dto == null ) {
            return null;
        }

        Car.CarBuilder car = Car.builder();

        car.brand( dto.getBrand() );
        car.description( dto.getDescription() );
        car.fuel( dto.isFuel() );
        car.name( dto.getName() );
        car.pic( dto.getPic() );
        car.price( dto.getPrice() );
        car.type( dto.getType() );
        car.year( dto.getYear() );

        return car.build();
    }

    @Override
    public User user(CreateUserDto UserDTO) {
        if ( UserDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( UserDTO.getEmail() );
        user.password( UserDTO.getPassword() );
        user.username( UserDTO.getUsername() );

        return user.build();
    }

    @Override
    public UsedCar usedCar(CreateCarDto UsedCarDTO) {
        if ( UsedCarDTO == null ) {
            return null;
        }

        UsedCar usedCar = new UsedCar();

        usedCar.setAddress( UsedCarDTO.getAddress() );
        usedCar.setBrand( UsedCarDTO.getBrand() );
        usedCar.setDescription( UsedCarDTO.getDescription() );
        usedCar.setFuel( UsedCarDTO.isFuel() );
        usedCar.setName( UsedCarDTO.getName() );
        usedCar.setOwnername( UsedCarDTO.getOwnername() );
        usedCar.setOwnernum( UsedCarDTO.getOwnernum() );
        usedCar.setPic( UsedCarDTO.getPic() );
        usedCar.setPrice( UsedCarDTO.getPrice() );
        usedCar.setType( UsedCarDTO.getType() );
        usedCar.setYear( UsedCarDTO.getYear() );

        return usedCar;
    }

    @Override
    public UsedCar updateCarToUsedCar(Car dto) {
        if ( dto == null ) {
            return null;
        }

        UsedCar usedCar = new UsedCar();

        usedCar.setBrand( dto.getBrand() );
        usedCar.setName( dto.getName() );
        usedCar.setDescription( dto.getDescription() );
        usedCar.setFuel( dto.getFuel() );
        usedCar.setPic( dto.getPic() );
        usedCar.setPrice( dto.getPrice() );
        usedCar.setType( dto.getType() );
        usedCar.setYear( dto.getYear() );

        return usedCar;
    }

    @Override
    public UsedCar updateUsedCar(UpdateCarDto updateCarDTO) {
        if ( updateCarDTO == null ) {
            return null;
        }

        UsedCar usedCar = new UsedCar();

        usedCar.setAddress( updateCarDTO.getAddress() );
        usedCar.setBrand( updateCarDTO.getBrand() );
        usedCar.setDescription( updateCarDTO.getDescription() );
        usedCar.setName( updateCarDTO.getName() );
        usedCar.setOwnername( updateCarDTO.getOwnername() );
        usedCar.setOwnernum( updateCarDTO.getOwnernum() );
        usedCar.setPic( updateCarDTO.getPic() );
        usedCar.setPrice( updateCarDTO.getPrice() );
        usedCar.setType( updateCarDTO.getType() );
        usedCar.setYear( updateCarDTO.getYear() );

        return usedCar;
    }

    @Override
    public void updateCarFromDto(UpdateCarDto dto, UsedCar entity) {
        if ( dto == null ) {
            return;
        }

        entity.setAddress( dto.getAddress() );
        entity.setBrand( dto.getBrand() );
        entity.setDescription( dto.getDescription() );
        entity.setFuel( dto.getFuel() );
        entity.setName( dto.getName() );
        entity.setOwnername( dto.getOwnername() );
        entity.setOwnernum( dto.getOwnernum() );
        entity.setPic( dto.getPic() );
        entity.setPrice( dto.getPrice() );
        entity.setType( dto.getType() );
        entity.setYear( dto.getYear() );
    }

    @Override
    public void updateCarFromDto(UpdateCarDto dto, Car entity) {
        if ( dto == null ) {
            return;
        }

        entity.setBrand( dto.getBrand() );
        entity.setDescription( dto.getDescription() );
        entity.setFuel( dto.getFuel() );
        entity.setName( dto.getName() );
        entity.setPic( dto.getPic() );
        entity.setPrice( dto.getPrice() );
        entity.setType( dto.getType() );
        entity.setYear( dto.getYear() );
    }

    @Override
    public void updateCarFromUsedCarDto(UpdateCarDto dto, Car car) {
        if ( dto == null ) {
            return;
        }

        car.setBrand( dto.getBrand() );
        car.setDescription( dto.getDescription() );
        car.setFuel( dto.getFuel() );
        car.setName( dto.getName() );
        car.setPic( dto.getPic() );
        car.setPrice( dto.getPrice() );
        car.setType( dto.getType() );
        car.setYear( dto.getYear() );
    }
}
