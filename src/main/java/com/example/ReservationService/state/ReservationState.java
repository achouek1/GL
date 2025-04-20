package com.example.ReservationService.state;

import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.enums.ReservationStatus;

public interface ReservationState {
    void approve(Reservation reservation);
    void reject(Reservation reservation);
    ReservationStatus getStatus();
}