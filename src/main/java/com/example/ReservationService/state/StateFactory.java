package com.example.ReservationService.state;

import com.example.ReservationService.enums.ReservationStatus;
import java.util.EnumMap;
import java.util.Map;

public class StateFactory {
    private static final Map<ReservationStatus, ReservationState> states = new EnumMap<>(ReservationStatus.class);

    // Initialisation des états par défaut
    static {
        registerState(ReservationStatus.ENATTENTE, new PendingState());
        registerState(ReservationStatus.APPROUVÉ, new ApprovedState());
        registerState(ReservationStatus.REJETÉ, new RejectedState());
    }

    // Méthode pour enregistrer de nouveaux états
    public static void registerState(ReservationStatus status, ReservationState state) {
        states.put(status, state);
    }

    // Méthode pour obtenir un état
    public static ReservationState getState(ReservationStatus status) {
        return states.getOrDefault(status, new PendingState()); // Fallback sécurisé
    }
}