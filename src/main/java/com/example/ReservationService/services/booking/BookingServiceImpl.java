package com.example.ReservationService.services.booking;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.repository.ReservationRepository;
import com.example.ReservationService.repository.ServiceRepository;
import com.example.ReservationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public boolean bookService(ReservationDTO reservationDTO) {
        Optional<Ad> optionalAd = serviceRepository.findById(reservationDTO.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

        if(optionalAd.isPresent() && optionalUser.isPresent()){
            // Utilisation du Creator via DTO
            Reservation reservation = reservationDTO.toReservation(optionalUser.get(), optionalAd.get());

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public List<ReservationDTO> getAllBookingsByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDTO).collect(Collectors.toList());

    }
}
