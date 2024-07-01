package com.ics.springnuxt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ics.springnuxt.dto.CreateCarDto;
import com.ics.springnuxt.dto.ShowroomDto;
import com.ics.springnuxt.dto.UpdateCarDto;
import com.ics.springnuxt.dto.UsedCarDto;
import com.ics.springnuxt.entity.Car;
import com.ics.springnuxt.entity.UsedCar;
import com.ics.springnuxt.mapper.DTOToObjectMapper;
import com.ics.springnuxt.repository.CarRepository;
import com.ics.springnuxt.repository.UsedCarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UsedCarRepository usedcarRepository;
	
	@Autowired
	private DTOToObjectMapper carMapper; 


	public CarService (CarRepository carRepository,  UsedCarService usedcarService) {
		this.carRepository = carRepository;
	}

	public ShowroomDto createCar(CreateCarDto createCarDTO) 
	{
		Car carx = carMapper.car(createCarDTO)    ;   
		
		Car showroom = carRepository.save(carx);
		return new ShowroomDto(showroom);
	}

	public List<ShowroomDto> getCars() {
		List<Car> carx = carRepository.findAll();
		return carx.stream().map(entity -> new ShowroomDto(entity)).toList();
	}

	public ShowroomDto getCarById(Integer id) {
		Optional<Car> carx = carRepository.findById(id);
		if (carx.isPresent()) {
			return new ShowroomDto(carx.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car ID not found");
		}
	}


	
    public ShowroomDto updateCar(String id, UpdateCarDto updateCarDTO) {
        Optional<Car> carx = carRepository.findByName(id);
        if (carx.isPresent()) {
        	carMapper.updateCarFromDto(updateCarDTO, carx.get());
            carRepository.save(carx.get());
            return new ShowroomDto(carx.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car update not succeeded");
        }
    }

	
	

	public void deleteCars(Long id) {
		Optional<Car> carx = carRepository.findById(id);
		if (carx.isPresent()) {
			carRepository.delete(carx.get());
		} else {
			throw new RuntimeException("No Car Deleted");
		}
	}

	public ShowroomDto getCarByUsername(String name) {
		Optional<Car> carx = carRepository.findByName(name);	
		if (carx.isPresent()) {
			return new ShowroomDto(carx.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car by name"+ name + "not found");
		}
	}



	public List<ShowroomDto> getCarByBrand(String brand) {
	    List<Car> cars = carRepository.findByBrand(brand);
	    return cars.stream()
	            .map(car -> new ShowroomDto(
	                    null,
	                    car.getName(),
	                    car.getBrand(),
	                    car.getDescription(),
	                    car.getPrice(),
	                    car.getType(),
	                    car.getYear(),
	                    car.getPic(),
	                    null))
	            .collect(Collectors.toList());
	}




	public List<String> getAllBrands() {
		return carRepository.findAllDistinctBrands();
	}
	
	
		
	 public UsedCarDto buyCar(String name, UpdateCarDto updateCarDTO) {
	        Optional<Car> carx = carRepository.findByName(name);
	        if (carx.isPresent()) {
	            carMapper.updateCarFromUsedCarDto(updateCarDTO, carx.get());
	            UsedCar carz = carMapper.updateUsedCar(updateCarDTO);
	            carRepository.save(carx.get());
	            usedcarRepository.save(carz);
	            return new UsedCarDto(carz);
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car update not succeeded");
	        }
	    }

}
