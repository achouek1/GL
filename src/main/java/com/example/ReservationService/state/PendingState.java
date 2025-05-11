package com.example.ReservationService.state;

import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.enums.ReservationStatus;

public class PendingState implements ReservationState {
    @Override
    public void approve(Reservation reservation) {
        reservation.setReservationStatus(ReservationStatus.APPROUVÉ);
    }
    @Override
    public ReservationStatus getStatus() {
        return ReservationStatus.ENATTENTE;
    }

    @Override
    public void reject(Reservation reservation) {
        reservation.setReservationStatus(ReservationStatus.REJETÉ);
    }
}