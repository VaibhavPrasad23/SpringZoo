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
	private DTOToObjectMapper objectMapper; 


	public CarService (CarRepository carRepository,  UsedCarService usedcarService) {
		this.carRepository = carRepository;
	}

	public ShowroomDto createCar(CreateCarDto createCarDTO) 
	{
		Car carx = objectMapper.car(createCarDTO)    ;   
		
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

	public ShowroomDto updateCar(String name, UpdateCarDto updateCarDTO) {
		Optional<Car> carx = carRepository.findByName(name);
		if (carx.isPresent()) {
			carx.get().setName(updateCarDTO.getName());
			carx.get().setBrand(updateCarDTO.getBrand());
			carx.get().setDescription(updateCarDTO.getDescription());
			carx.get().setPrice(updateCarDTO.getPrice());
			carx.get().setType(updateCarDTO.getType());
			carx.get().setYear (updateCarDTO.getYear());
			carx.get().setPic (updateCarDTO.getPic());

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
		UsedCar carz = new UsedCar();        


		if (carx.isPresent()) {
			

			carx.get().setName(updateCarDTO.getName());
			carz.setName(carx.get().getName());

			
			carx.get().setBrand(updateCarDTO.getBrand());
			carz.setBrand(carx.get().getBrand());
			
			carx.get().setDescription(updateCarDTO.getDescription());
			carz.setDescription(carx.get().getDescription());
			
			carx.get().setPrice(updateCarDTO.getPrice());
			carz.setPrice(carx.get().getPrice());
			
			carx.get().setType(updateCarDTO.getType());
			carz.setType(carx.get().getType());
			
			carx.get().setYear (updateCarDTO.getYear());
			carz.setYear(carx.get().getYear());
			
			carx.get().setPic (updateCarDTO.getPic());
			carz.setPic(carx.get().getPic());
			
	
			carz.setOwnername(updateCarDTO.getOwnername());
			carz.setOwnernum(updateCarDTO.getOwnernum());
			carz.setAddress(updateCarDTO.getAddress());
						
			usedcarRepository.save(carz);			
			carRepository.save(carx.get());
			
			new ShowroomDto(carx.get());
			return new UsedCarDto(carz);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car update not succeeded");
		}
	}
	
	
	

}
