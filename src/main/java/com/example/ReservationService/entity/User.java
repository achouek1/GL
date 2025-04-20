package com.example.ReservationService.entity;

import com.example.ReservationService.dto.UserDto;
import com.example.ReservationService.enums.UserRole;
import com.example.ReservationService.observer.ReservationObserver;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements ReservationObserver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserDto getDto() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setLastname(lastname);
        userDto.setPhone(phone);
        userDto.setRole(role);
        return userDto;
    }

    @Override
    public void update(Reservation reservation) {
        // ✅ Seuls les clients sont notifiés
        if (this.role == UserRole.CLIENT) {
            System.out.println("Notification envoyée à " + this.email +
                    ": Votre réservation est maintenant " + reservation.getReservationStatus());
        }
    }
}
