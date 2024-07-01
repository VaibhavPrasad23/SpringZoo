package com.ics.springnuxt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ics.springnuxt.dto.CreateUserDto;
import com.ics.springnuxt.dto.UpdateUserDto;
import com.ics.springnuxt.dto.UserDto;
import com.ics.springnuxt.entity.User;
import com.ics.springnuxt.mapper.DTOToObjectMapper;
import com.ics.springnuxt.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	private DTOToObjectMapper userMapper; 

	
	public UserDto createUsername(CreateUserDto createUserDTO) 
	{
		User userx = userMapper.user(createUserDTO);   
		
		User newUser = userRepository.save(userx);
		return new UserDto(newUser);
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
	
	

	public UserDto loginUser(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid username or password");
		}
		return new UserDto(user);
	}
	
}
