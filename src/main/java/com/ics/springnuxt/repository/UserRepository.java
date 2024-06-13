package com.ics.springnuxt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ics.springnuxt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findById(Integer id);


}
