package com.example.ReservationService.dto;

import com.example.ReservationService.enums.UserRole;
import lombok.Data;

@Data
public class SignupRequestDTO {
    private Long id;

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
}
