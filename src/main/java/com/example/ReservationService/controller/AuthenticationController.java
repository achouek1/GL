package com.example.ReservationService.controller;

import com.example.ReservationService.dto.AuthenticationRequest;
import com.example.ReservationService.dto.SignupRequestDTO;
import com.example.ReservationService.dto.UserDto;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.repository.UserRepository;
import com.example.ReservationService.services.authentication.AuthService;
import com.example.ReservationService.services.jwt.UserDetailsServiceImpl;
import com.example.ReservationService.utill.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class AuthenticationController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    private final JwtUtil jwtUtil = JwtUtil.getInstance();

    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING ="Authorization";


    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/client/sign-up")

    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDTO signupRequestDTO){
        if (authService.presentByEmail(signupRequestDTO.getEmail())) {
            return new ResponseEntity<>("Client existe  avec cet email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.signupClient(signupRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }






    @PostMapping("/company/sign-up")

    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDTO signupRequestDTO){
        if (authService.presentByEmail(signupRequestDTO.getEmail())) {
            return new ResponseEntity<>("Company existe deja avec cet email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.signupCompany(signupRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }





    @PostMapping({"/authenticate"})

    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response)  throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword()
            ));
        }catch (BadCredentialsException e) {
            throw new BadCredentialsException("Login ou Mot de passe incorrecte",e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt= jwtUtil.generateToken(userDetails.getUsername());
        User user=userRepository.findFirstByEmail(authenticationRequest.getUsername());
        response.getWriter().write(new JSONObject()
                .put("userId",user.getId())
                .put("role",user.getRole())
                .toString()
        );
        response.addHeader("Access-Control-Expose-Headers","Authorization");
        response.addHeader("Access-Control-Allow-Headers","Authorization,"+
                "X-PINGOTHER,Origin,X-Requested-With,Content-Type,Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);
    }











}
