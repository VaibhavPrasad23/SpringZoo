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

import com.ics.springnuxt.dto.CreateUserDto;
import com.ics.springnuxt.dto.UpdateUserDto;
import com.ics.springnuxt.dto.UserDto;
import com.ics.springnuxt.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }
    
    //SAVE USER
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto newUser) {
        UserDto userDTO = userService.createUsername(newUser);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    //GET ALL USERS
    @GetMapping("")
    public List<UserDto> getUsers(@RequestParam Optional<String> id) {
        if (id.isPresent()) {
            return userService.getUsernames(id.get());
        }
        return userService.getUsers();
    }

    
    //GET BY USER BY ID
    @GetMapping("/id/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getUsersById(id);
    }
    
    
    //LOGIN
    @PostMapping("/login")
    public UserDto login(@RequestBody CreateUserDto user) {
        return userService.loginUser(user.getUsername(), user.getPassword());
    }
    

    
    
    // NOT USED
    
    //GET BY USER USERNAME
    @GetMapping("/userid/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    //UPDATE USER
    @PutMapping("/update/{username}")
    public UserDto updateUser(@PathVariable String username, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUsers(username, updateUserDto);
    }

    
    //DELETE USER
	@DeleteMapping("/delete/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        userService.deleteUsers(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
