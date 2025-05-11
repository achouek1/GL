package com.example.ReservationService.dto;

import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.enums.ReservationStatus;
import com.example.ReservationService.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    private Long id;
    private Date bookDate;
    private String serviceName;
 private ReservationStatus reservationStatus;
 private ReviewStatus reviewStatus;
 private Long userId;
 private String userName;
    private Long companyId;
    private Long adId;

    // Méthode de création (GRASP Creator):création de reservation
    public Reservation toReservation(User user, Ad ad) {
        Reservation reservation = new Reservation();
        reservation.setBookDate(this.bookDate);
        reservation.setReservationStatus(ReservationStatus.ENATTENTE);
        reservation.setReviewStatus(ReviewStatus.FAUX);
        reservation.setUser(user);
        reservation.setAd(ad);
        reservation.setCompany(ad.getUser());
        return reservation;
    }



}
