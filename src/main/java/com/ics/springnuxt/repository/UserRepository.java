package com.ics.springnuxt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.ics.springnuxt.entity.User;

public interface UserRepository extends RevisionRepository<User, Integer, Integer>, JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Optional<User> findById(Integer id)	;

	User findByUsernameAndPassword(String username, String password);


}
