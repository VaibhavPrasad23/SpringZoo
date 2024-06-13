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
    

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto newUser) {
        UserDto userDTO = userService.createUsername(newUser);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<UserDto> getUsers(@RequestParam Optional<String> id) {
        if (id.isPresent()) {
            return userService.getUsernames(id.get());
        }
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getUsersById(id);
    }
    
    @GetMapping("/userid/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/up/{username}")
    public UserDto updateUser(@PathVariable String username, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUsers(username, updateUserDto);
    }

	@DeleteMapping("/del/{username}")
    public ResponseEntity deleteUser(@PathVariable String username) {
        userService.deleteUsers(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
