package com.example.ReservationService.services.booking;

import com.example.ReservationService.dto.ReservationDTO;
import java.util.List;

public interface BookingService {
    boolean bookService(ReservationDTO reservationDTO);
    List<ReservationDTO> getAllBookingsByUserId(Long userId);
}
