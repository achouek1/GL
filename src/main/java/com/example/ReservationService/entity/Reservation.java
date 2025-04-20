package com.example.ReservationService.entity;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.enums.ReservationStatus;
import com.example.ReservationService.enums.ReviewStatus;
import com.example.ReservationService.observer.ReservationObservable;
import com.example.ReservationService.observer.ReservationObserver;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
@Data
public class Reservation implements ReservationObservable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ReservationStatus reservationStatus;
    private ReviewStatus reviewStatus;
    private Date bookDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ad_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;

    @Transient
    private final List<ReservationObserver> observers = new ArrayList<>();

    public ReservationDTO getReservationDTO() {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(id);
        dto.setServiceName(ad.getServiceName());
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);
        dto.setAdId(ad.getId());
        dto.setCompanyId(company.getId());
        dto.setUserName(user.getName());
        return dto;
    }

    @Override
    public void addObserver(ReservationObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(ReservationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ReservationObserver observer : observers) {
            observer.update(this);
        }
    }

    public void setReservationStatusWithNotify(ReservationStatus status) {
        this.reservationStatus = status;
        notifyObservers();
    }
}