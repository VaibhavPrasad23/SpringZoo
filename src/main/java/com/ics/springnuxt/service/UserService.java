package com.ics.springnuxt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ics.springnuxt.dto.CreateUserDto;
import com.ics.springnuxt.dto.UpdateUserDto;
import com.ics.springnuxt.dto.UserDto;
import com.ics.springnuxt.model.User;
import com.ics.springnuxt.model.Zoo;
import com.ics.springnuxt.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDto createUsername(CreateUserDto createUserDTO) {
		User user = new User();
		user.setUsername(createUserDTO.getUsername());
		user.setEmail(createUserDTO.getEmail());
		user.setPassword(createUserDTO.getPassword());
		 User users = userRepository.save(user);
		return new UserDto(users);
	}

	public List<UserDto> getUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(entity -> new UserDto(entity)).toList();
	}

	public List<UserDto> getUsernames(String name) {
		Optional<User> users = userRepository.findByUsername(name);
		return users.stream().map(entity -> new UserDto(entity)).toList();
	}

	public UserDto getUsersById(Integer id) {
		Optional<User> zoo = userRepository.findById(id);
		if (zoo.isPresent()) {
			return new UserDto(zoo.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal ID not found");
		}
	}

	public UserDto updateUsers(String id, UpdateUserDto updateUserDto) {
		Optional<User> user = userRepository.findByUsername(id);
		if (user.isPresent()) {
            user.get().setUsername(updateUserDto.getUsername());
            user.get().setEmail(updateUserDto.getEmail());
            user.get().setPassword(updateUserDto.getPassword());

			userRepository.save(user.get());
			return new UserDto(user.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal update not succeeded");
		}
	}

	public void deleteUsers(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			userRepository.delete(user.get());
		} else {
			throw new RuntimeException("No Animal Deleted");
		}
	}

	public UserDto getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return new UserDto(user.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal by name" + username + "not found");
		}
	}
}
