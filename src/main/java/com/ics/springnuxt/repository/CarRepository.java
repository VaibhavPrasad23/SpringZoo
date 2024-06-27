package com.ics.springnuxt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ics.springnuxt.model.Zoo;

public interface ZooRepository extends JpaRepository<Zoo, Long> {
    List<Zoo> findByGender(Boolean gender);
    Optional<Zoo> findByAnimal(String username);
    
}
