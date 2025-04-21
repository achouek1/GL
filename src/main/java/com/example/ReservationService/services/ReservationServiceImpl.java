package com.example.ReservationService.services;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.mapper.ReservationMapper;
import com.example.ReservationService.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDTO(savedReservation);
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        return reservationMapper.toDTO(reservation);
    }
}