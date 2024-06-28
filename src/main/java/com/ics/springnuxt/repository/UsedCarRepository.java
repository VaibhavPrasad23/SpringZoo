package com.ics.springnuxt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import com.ics.springnuxt.entity.UsedCar;

public interface UsedCarRepository extends RevisionRepository<UsedCar, Integer, Integer> ,JpaRepository<UsedCar, Integer> {
    
	@Query("SELECT DISTINCT c.brand FROM Car c")
	List<String> findAllDistinctBrands();


	Optional<UsedCar> findByName(String name);

	Optional<UsedCar> findById(Long id);

	List<UsedCar> findByBrand(String brand);

}
