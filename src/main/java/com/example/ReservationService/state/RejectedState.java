package com.example.ReservationService.state;

import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.enums.ReservationStatus;

public class RejectedState implements ReservationState {
    @Override
    public void approve(Reservation reservation) {
        throw new IllegalStateException("Impossible d'approuver une réservation rejetée");
    }

    @Override
    public void reject(Reservation reservation) {
        throw new IllegalStateException("Déjà rejetée");
    }

    @Override
    public ReservationStatus getStatus() {  // Méthode manquante à ajouter
        return ReservationStatus.REJETÉ;
    }
}