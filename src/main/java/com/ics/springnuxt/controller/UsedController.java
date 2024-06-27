package com.ics.springnuxt.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ics.springnuxt.dto.CreateCarDto;
import com.ics.springnuxt.dto.UpdateCarDto;
import com.ics.springnuxt.dto.UsedCarDto;
import com.ics.springnuxt.entity.UsedCar;
import com.ics.springnuxt.service.UsedCarService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@RestController
@RequestMapping("/used")
public class UsedController {
	@Autowired
    private UsedCarService carusedService;


    @PersistenceContext
    private EntityManager entityManager;

    //CREATE CAR  
    @PostMapping("")
    public ResponseEntity<UsedCarDto> createCar(@Validated @RequestBody CreateCarDto newCAR) {
    	UsedCarDto carDTO = carusedService.createCar(newCAR);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }

    
    //GET ALL CARS
    @GetMapping("")
    public List<UsedCarDto> getCars(@RequestParam Optional<Boolean> fuel) {

        return carusedService.getCars();
    }

    //GET CAR BY ID
    @GetMapping("/{id}")
    public UsedCarDto getCarById(@PathVariable Integer id) {
        return carusedService.getCarById(id);
    }
    
    //GET BY CAR NAME
    @GetMapping("/car-model/{name}")
    public UsedCarDto getCarByUser(@PathVariable String name) {
        return carusedService.getCarByUsername(name);
    }
    
    //USED TO UPDATE CAR
    @PutMapping("updatecarown/{name}")
    public UsedCarDto udpateownerchange(@PathVariable String name, @RequestBody UpdateCarDto updateCarDto) {
    	return carusedService.updateCarOwner(name, updateCarDto);
    }
    
    
    // GETS REVISION FOR SINGLE ID[SPECIFIC]
    @GetMapping("/getInfo/{id}/{revision}")
    public @ResponseBody UsedCar getInfo(@PathVariable int id, @PathVariable int revision) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        List<UsedCar> carRevisions = auditReader.createQuery()
                .forRevisionsOfEntity(UsedCar.class, true, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();

        if (carRevisions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with ID " + id + " not found");
        } else if (revision < 0 || revision >= carRevisions.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Revision " + revision + " for car with ID " + id + " not found");
        } else {
            return carRevisions.get(revision);
        }
    }

    
    @GetMapping("/getInfo/{id}")
    public @ResponseBody List<UsedCar> getAllInfo(@PathVariable int id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        List<UsedCar> carRevisions = auditReader.createQuery()
                .forRevisionsOfEntity(UsedCar.class, true, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();

        if (carRevisions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with ID " + id + " not found");
        } else {
            return carRevisions;
        }
    }

    
///////////////////////////NOT USED///////////////////////////
    
    //CATEGORY
    @GetMapping("/categories")
    public List<String> getAllBrands() {
        return carusedService.getAllBrands();
    }

    //GET CARS OF SPECIFIC BRAND
    @GetMapping("/category/{brand}")
    public List<UsedCarDto> getCarByBrandz(@PathVariable String brand) {
        return carusedService.getCarByBrand(brand);
    }
    
    //GET IMAGE OF THE CAR[URI]
    @GetMapping("/pic/{pic}")
    public String getCarImage(@PathVariable String pic) {
        return carusedService.getCarImage(pic);
    }
    
    // UPDATE CAR by name
    @PutMapping("/carname/{id}")
    public UsedCarDto updateCar(@PathVariable String id, @RequestBody UpdateCarDto updateCarDto) {
        return carusedService.updateCar(id, updateCarDto);
    }
    
    
    
    //DELETE CAR
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carusedService.deleteCars(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
