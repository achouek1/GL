package com.example.ReservationService.entity;


import com.example.ReservationService.dto.UserDto;
import com.example.ReservationService.enums.UserRole;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private UserRole role;


    public UserDto getDto(){
        UserDto  userDto=new UserDto();

        userDto.setId(id);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setLastname(lastname);
        userDto.setPhone(phone);
        userDto.setRole(role);
        return userDto;

    }


}
