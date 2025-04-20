package com.example.ReservationService.services.authentication;

import com.example.ReservationService.dto.UserDto;
import com.example.ReservationService.repository.UserRepository;
import com.example.ReservationService.dto.SignupRequestDTO;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService{

    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient (SignupRequestDTO signupRequestDTO) {

        User user =new User();

        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));


        user.setRole(UserRole.CLIENT);
//	return userRepository.save(user).getDto();

        return  userRepository.save(user).getDto();

    }
    public Boolean presentByEmail(String email) {
        return userRepository.findFirstByEmail(email)!= null;
    }

    public UserDto signupCompany (SignupRequestDTO signupRequestDTO) {

        User user =new User();

        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));


        user.setRole(UserRole.COMPANY);
//	return userRepository.save(user).getDto();

        return  userRepository.save(user).getDto();

    }



}
