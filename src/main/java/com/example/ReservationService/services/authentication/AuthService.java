package com.example.ReservationService.services.authentication;

import com.example.ReservationService.dto.SignupRequestDTO;
import com.example.ReservationService.dto.UserDto;

public interface AuthService {
    UserDto signupClient (SignupRequestDTO signupRequestDTO);
    Boolean presentByEmail(String email);
    UserDto signupCompany (SignupRequestDTO signupRequestDTO);
}
