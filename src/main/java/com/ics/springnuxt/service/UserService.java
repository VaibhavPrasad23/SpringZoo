package com.ics.springnuxt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ics.springnuxt.dto.CreateUserDto;
import com.ics.springnuxt.dto.UpdateUserDto;
import com.ics.springnuxt.dto.UserDto;
import com.ics.springnuxt.entity.User;
import com.ics.springnuxt.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDto createUsername(CreateUserDto createUserDTO) {
		User user = new User();
		user.setUsername(createUserDTO.getUsername());
		user.setEmail(createUserDTO.getEmail());
		user.setPassword(createUserDTO.getPassword());
		User savedUser = userRepository.save(user);
		return new UserDto(savedUser);
	}

	public List<UserDto> getUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}

	public List<UserDto> getUsernames(String name) {
		Optional<User> users = userRepository.findByUsername(name);
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}

	public UserDto getUsersById(Integer id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID " + id + " not found"));
		return new UserDto(user);
	}

	public UserDto updateUsers(String username, UpdateUserDto updateUserDto) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User update not succeeded"));
		user.setUsername(updateUserDto.getUsername());
		user.setEmail(updateUserDto.getEmail());
		user.setPassword(updateUserDto.getPassword());
		User updatedUser = userRepository.save(user);
		return new UserDto(updatedUser);
	}

	public void deleteUsers(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + username + " not found"));
		userRepository.delete(user);
	}

	public UserDto getUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User by name " + username + " not found"));
		return new UserDto(user);
	}
}
