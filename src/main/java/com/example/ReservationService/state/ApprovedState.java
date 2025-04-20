package com.example.ReservationService.state;

import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.enums.ReservationStatus;

public class ApprovedState implements ReservationState {
    @Override
    public void approve(Reservation reservation) {
        throw new IllegalStateException("Déjà approuvée");
    }
    @Override
    public ReservationStatus getStatus() {
        return ReservationStatus.APPROUVÉ;
    }
    @Override
    public void reject(Reservation reservation) {
        reservation.setReservationStatus(ReservationStatus.REJETÉ);
    }
}