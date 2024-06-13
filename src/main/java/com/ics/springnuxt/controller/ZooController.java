package com.ics.springnuxt.controller;

import java.util.List;
import java.util.Optional;

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

import com.ics.springnuxt.dto.CreateAnimalDto;
import com.ics.springnuxt.dto.UpdateAnimalDto;
import com.ics.springnuxt.dto.ZooDto;
import com.ics.springnuxt.service.ZooService;


@RestController
@RequestMapping("/zoo")
public class ZooController {
    private ZooService zooService;

    public ZooController (ZooService zooService) {
        this.zooService = zooService;
    }
    

    @PostMapping("")
    public ResponseEntity<ZooDto> createAnimal(@RequestBody CreateAnimalDto newToDo) {
        ZooDto zooDTO = zooService.createAnimal(newToDo);
        return new ResponseEntity<>(zooDTO, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<ZooDto> getAnimals(@RequestParam Optional<Boolean> completed) {
        if (completed.isPresent()) {
            return zooService.getAnimals(completed.get());
        }
        return zooService.getAnimals();
    }

    @GetMapping("/{id}")
    public ZooDto getAnimalById(@PathVariable Long id) {
        return zooService.getAnimalById(id);
    }
    
    @GetMapping("/ani/{animal}")
    public ZooDto getAnimalByUser(@PathVariable String animal) {
        return zooService.getAnimalByUsername(animal);
    }

    @PutMapping("/ani/{id}")
    public ZooDto updateAnimal(@PathVariable Long id, @RequestBody UpdateAnimalDto updateAnimalDto) {
        return zooService.updateToDo(id, updateAnimalDto);
    }

    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    public ResponseEntity deleteToDo(@PathVariable Long id) {
        zooService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
