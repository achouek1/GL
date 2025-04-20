package com.example.ReservationService.entity;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.enums.ReservationStatus;
import com.example.ReservationService.enums.ReviewStatus;
import com.example.ReservationService.state.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    @Transient
    private ReservationState currentState;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_status")
    private ReviewStatus reviewStatus;

    @Column(name = "book_date")
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

    // Initialise l'état après le chargement depuis la base
    @PostLoad
    private void initState() {
        this.currentState = switch(this.reservationStatus) {
            case ENATTENTE -> new PendingState();
            case APPROUVÉ -> new ApprovedState();
            case REJETÉ -> new RejectedState();
            default -> throw new IllegalStateException("Statut inconnu: " + this.reservationStatus);
        };
    }

    // Méthodes pour gérer les transitions d'état
    public void approve() {
        this.currentState.approve(this);
        this.reservationStatus = this.currentState.getStatus();
    }

    public void reject() {
        this.currentState.reject(this);
        this.reservationStatus = this.currentState.getStatus();
    }


    // Conversion en DTO
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
}