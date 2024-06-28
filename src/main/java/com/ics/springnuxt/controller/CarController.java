package com.ics.springnuxt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ics.springnuxt.dto.CreateCarDto;
import com.ics.springnuxt.dto.ShowroomDto;
import com.ics.springnuxt.dto.UpdateCarDto;
import com.ics.springnuxt.dto.UsedCarDto;
import com.ics.springnuxt.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
    private CarService carService;

    public CarController (CarService carService) {
        this.carService = carService;
    }
    
    //CREATE CAR
    @PostMapping()
    public ResponseEntity<ShowroomDto> createCar(@RequestBody CreateCarDto newCAR) 
    {
    	ShowroomDto carDTO = carService.createCar(newCAR);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }
    
    
    //GET ALL CARS
    @GetMapping()
    public List<ShowroomDto> getCars(@RequestParam Optional<Boolean> fuel) {

        return carService.getCars();
    }
    
    
    //GETS CAR BY ID
    @GetMapping("/{id}")
    public ShowroomDto getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }
    
    //GET CAR BY NAME
    @GetMapping("/car-model/{name}")
    public ShowroomDto getCarByUser(@PathVariable String name) {
        return carService.getCarByUsername(name);
    }
    
    //BUY CAR AND POSTS TO USEDCARS
    @PostMapping("/buyCar/{id}")
    public UsedCarDto buyCar(@PathVariable String id, @RequestBody UpdateCarDto updateCarDto) {
        return carService.buyCar(id, updateCarDto);
        
    }
    
    
    ///////////////////////////NOT USED///////////////////////////
    
    //GET ALL BRANDS
    @GetMapping("/categories")
    public List<String> getAllBrands() {
        return carService.getAllBrands();
    }

    
    //GET CARS OF SPECIFIC BRAND
    @GetMapping("/category/{brand}")
    public List<ShowroomDto> getCarByBrandz(@PathVariable String brand) {
        return carService.getCarByBrand(brand);
    }
    
    
    //UPDATE CAR by name
    @PutMapping("/car-name/{name}")
    public ShowroomDto updateCar(@PathVariable String name, @RequestBody UpdateCarDto updateCarDto) {
        return carService.updateCar(name, updateCarDto);
    }
    


    //DELETE CAR BY ID
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCars(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
