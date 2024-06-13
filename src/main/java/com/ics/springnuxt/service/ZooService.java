package com.ics.springnuxt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ics.springnuxt.dto.CreateAnimalDto;
import com.ics.springnuxt.dto.UpdateAnimalDto;
import com.ics.springnuxt.dto.ZooDto;
import com.ics.springnuxt.model.Zoo;
import com.ics.springnuxt.repository.ZooRepository;

@Service
public class ZooService {
    private ZooRepository zooRepository;

    public ZooService (ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
    }

    public ZooDto createAnimal(CreateAnimalDto createZooDTO) {
        Zoo animal = new Zoo();        
        animal.setAnimal(createZooDTO.getAnimal());
        animal.setTransfer(createZooDTO.getTransfer());
        animal.setQuantity(createZooDTO.getQuantity());
        animal.setFood(createZooDTO.getFood());
        animal.setGender (createZooDTO.getGender());
        Zoo zoo = zooRepository.save(animal);
        return new ZooDto(zoo);
    }

    public List<ZooDto> getAnimals() {
        List<Zoo> zoos = zooRepository.findAll();
        return zoos.stream().map(entity -> new ZooDto(entity)).toList();
    }

    public List<ZooDto> getAnimals(Boolean gender) {
        List<Zoo> zoos = zooRepository.findByGender(gender);
        return zoos.stream().map(entity -> new ZooDto(entity)).toList();
    }

    public ZooDto getAnimalById(Long id) {
        Optional<Zoo> zoo = zooRepository.findById(id);
        if (zoo.isPresent()) {
            return new ZooDto(zoo.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal ID not found");
        }
    }

    public ZooDto updateToDo(Long id, UpdateAnimalDto updateZooDto) {
        Optional<Zoo> zoo = zooRepository.findById(id);
        if (zoo.isPresent()) {
            zoo.get().setAnimal(updateZooDto.getAnimal());
            zoo.get().setTransfer(updateZooDto.getTransfer());
            zoo.get().setQuantity(updateZooDto.getQuantity());
            zoo.get().setFood(updateZooDto.getFood());
            zoo.get().setGender (updateZooDto.getGender());
            zooRepository.save(zoo.get());
            return new ZooDto(zoo.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal update not succeeded");
        }
    }

    public void deleteToDo(Long id) {
        Optional<Zoo> zoo = zooRepository.findById(id);
        if (zoo.isPresent()) {
            zooRepository.delete(zoo.get());
        } else {
            throw new RuntimeException("No Animal Deleted");
        }
    }
    
    public ZooDto getAnimalByUsername(String animal) {
        Optional<Zoo> zoo = zooRepository.findByAnimal(animal);	
        if (zoo.isPresent()) {
            return new ZooDto(zoo.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal by name"+ animal + "not found");
        }
    }
}
