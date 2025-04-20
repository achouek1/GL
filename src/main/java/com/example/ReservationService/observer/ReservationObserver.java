package com.example.ReservationService.observer;

import com.example.ReservationService.entity.Reservation;

public interface ReservationObserver {
    void update(Reservation reservation);
}
