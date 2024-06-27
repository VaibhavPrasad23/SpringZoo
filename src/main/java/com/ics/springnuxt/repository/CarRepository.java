package com.ics.springnuxt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import com.ics.springnuxt.entity.Car;

public interface CarRepository extends RevisionRepository<Car, Integer, Integer>, JpaRepository<Car, Integer> {
    List<Car> findByFuel(Boolean fuel);
    Optional<Car> findByName(String name);
	Optional<Car> findById(Long id);
    List<Car> findByBrand(String brand);
    
    @Query("SELECT DISTINCT c.brand FROM Car c")
    List<String> findAllDistinctBrands();
	List<Car> findByType(String type);
}
