package com.example.ReservationService.state;
import com.example.ReservationService.enums.ReservationStatus;

public class StateFactory {
    // Implémentation du Singleton (volatile + double-checked locking)
    private static volatile StateFactory instance;

    private StateFactory() {}

    public static StateFactory getInstance() {
        if (instance == null) {
            synchronized (StateFactory.class) {
                if (instance == null) {
                    instance = new StateFactory();
                }
            }
        }
        return instance;
    }

    // Méthode pour créer les états
    public ReservationState createState(ReservationStatus status) {
        switch (status) {
            case ENATTENTE:
                return new PendingState();
            case APPROUVÉ:
                return new ApprovedState();
            case REJETÉ:
                return new RejectedState();
            default:
                throw new IllegalArgumentException("Statut non supporté : " + status);
        }
    }
}