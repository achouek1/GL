package com.example.ReservationService;

import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.enums.ReservationStatus;
import com.example.ReservationService.enums.UserRole;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

	@PostConstruct
	public void testObserverPattern() {
		System.out.println(">>> Méthode testObserverPattern appelée !");

		// Crée une réservation
		Reservation reservation = new Reservation();

		// Crée un utilisateur (client)
		User client = new User();
		client.setEmail("client@example.com");
		client.setRole(UserRole.CLIENT); // ✅ Nécessaire pour déclencher la notification

		// Ajoute le client comme observateur
		reservation.addObserver(client);
		System.out.println(">>> Observateur ajouté.");

		// Modifie le statut de la réservation -> déclenche la notification
		reservation.setReservationStatusWithNotify(ReservationStatus.APPROUVÉ);
	}
}
