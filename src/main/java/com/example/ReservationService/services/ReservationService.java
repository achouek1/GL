package com.example.ReservationService.services;

import com.example.ReservationService.dto.ReservationDTO;

public interface ReservationService {
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO getReservationById(Long id);
}