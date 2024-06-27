package com.ics.springnuxt.dto;

import com.ics.springnuxt.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String password;


    public UserDto(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }
}
