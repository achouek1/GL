package com.example.ReservationService.factory;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.enums.ReservationStatus;


public class ReservationFactory {
    public static Reservation createReservation(
            ReservationDTO reservationDTO,
            User user,
            Ad ad
    ) {
        Reservation reservation = new Reservation();
        reservation.setBookDate(reservationDTO.getBookDate());
        reservation.setUser(user);
        reservation.setAd(ad);
        reservation.setReservationStatus(ReservationStatus.ENATTENTE);
        return reservation;
    }
}
