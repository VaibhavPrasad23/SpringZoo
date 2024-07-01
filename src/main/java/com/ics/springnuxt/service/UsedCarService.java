package com.ics.springnuxt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ics.springnuxt.dto.CreateCarDto;
import com.ics.springnuxt.dto.UpdateCarDto;
import com.ics.springnuxt.dto.UsedCarDto;
import com.ics.springnuxt.entity.UsedCar;
import com.ics.springnuxt.mapper.DTOToObjectMapper;
import com.ics.springnuxt.repository.UsedCarRepository;

@Service
	
public class UsedCarService {
	@Autowired
	private UsedCarRepository usedcarRepository;
	

	public UsedCarService (UsedCarRepository carRepository) {
		this.usedcarRepository = carRepository;
	}

	@Autowired
	private DTOToObjectMapper usedcarMapper; 
	
	
	public UsedCarDto createCar(CreateCarDto createCarDTO) {
		
		UsedCar carx = usedcarMapper.usedCar(createCarDTO); 
		UsedCar usedCar = usedcarRepository.save(carx);
		return new UsedCarDto(usedCar);

	}

	
	public List<UsedCarDto> getCars() {
		List<UsedCar> carx = usedcarRepository.findAll();
		return carx.stream().map(entity -> new UsedCarDto(entity)).toList();
	}

	
	public UsedCarDto getCarById(Integer id) {
		Optional<UsedCar> carx = usedcarRepository.findById(id);
		if (carx.isPresent()) {
			return new UsedCarDto(carx.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car ID not found");
		}
	}

	
    public UsedCarDto updateCar(String id, UpdateCarDto updateCarDTO) {
        Optional<UsedCar> carx = usedcarRepository.findByName(id);
        if (carx.isPresent()) {
        	usedcarMapper.updateCarFromDto(updateCarDTO, carx.get());
            usedcarRepository.save(carx.get());
            return new UsedCarDto(carx.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car update not succeeded");
        }
    }

	
	public UsedCarDto updateCarOwner(String id, UpdateCarDto updateCarDTO) {
		Optional<UsedCar> carx = usedcarRepository.findByName(id);
		if (carx.isPresent()) {
			carx.get().setPrice(updateCarDTO.getPrice());
			carx.get().setOwnername(updateCarDTO.getOwnername());	
			carx.get().setOwnernum(updateCarDTO.getOwnernum());
			carx.get().setAddress(updateCarDTO.getAddress());
			usedcarRepository.save(carx.get());
			return new UsedCarDto(carx.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car update not succeeded");
		}
	} 
	
	
	public void deleteCars(Long id) {
		Optional<UsedCar> carx = usedcarRepository.findById(id);
		if (carx.isPresent()) {
			usedcarRepository.delete(carx.get());
		} else {
			throw new RuntimeException("No Car Deleted");
		}
	}

	public UsedCarDto getCarByUsername(String name) {
		Optional<UsedCar> carx = usedcarRepository.findByName(name);	
		if (carx.isPresent()) {
			return new UsedCarDto(carx.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car by name"+ name + "not found");
		}
	}


	public List<UsedCarDto> getCarByBrand(String brand) {
	    List<UsedCar> cars = usedcarRepository.findByBrand(brand);
	    return cars.stream()
	            .map(car -> new UsedCarDto(
	                    null,
	                    car.getName(),
	                    car.getBrand(),
	                    car.getDescription(),
	                    car.getPrice(),
	                    car.getType(),
	                    car.getYear(),
	                    car.getPic(),
	                    car.getOwnername(),
	                    car.getOwnernum(),
	                    car.getAddress(),
	                    null))
	            .collect(Collectors.toList());
	}


	public List<String> getAllBrands() {
		return usedcarRepository.findAllDistinctBrands();
	}

	public String getCarImage(String id) {
		Optional<UsedCar> carx = usedcarRepository.findByName(id);
		return carx.get().getPic();
	}
	


}
