package com.example.ReservationService.services.company;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.dto.ServiceDTO;
import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.enums.ReservationStatus;
import com.example.ReservationService.repository.ReservationRepository;
import com.example.ReservationService.repository.ServiceRepository;
import com.example.ReservationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public CompanyServiceImpl(UserRepository userRepository, ServiceRepository serviceRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public boolean postService(Long userId, ServiceDTO serviceDTO) throws IOException {
        // ✅ Précondition : le prix doit être strictement positif
        if (serviceDTO.getPrice() <= 0) {
            throw new IllegalArgumentException("Le prix du service doit être supérieur à 0.");
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Ad ad = new Ad();
            ad.setServiceName(serviceDTO.getServiceName());
            ad.setDescription(serviceDTO.getDescription());
            ad.setImg(serviceDTO.getImg().getBytes());
            ad.setPrice(serviceDTO.getPrice());
            ad.setUser(optionalUser.get());

            serviceRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public List<ServiceDTO> getAllServices(Long userId) {
        return serviceRepository.findAllByUserId(userId)
                .stream()
                .map(Ad::getServiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDTO getAdById(Long adId) {
        return serviceRepository.findById(adId)
                .map(Ad::getServiceDTO)
                .orElse(null);
    }

    @Override
    public boolean updateAd(Long adId, ServiceDTO serviceDTO) throws IOException {
        Optional<Ad> optionalAd = serviceRepository.findById(adId);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            ad.setServiceName(serviceDTO.getServiceName());
            ad.setDescription(serviceDTO.getDescription());
            ad.setPrice(serviceDTO.getPrice());

            if (serviceDTO.getImg() != null) {
                ad.setImg(serviceDTO.getImg().getBytes());
            }

            serviceRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAd(Long adId) {
        return serviceRepository.findById(adId).map(ad -> {
            serviceRepository.delete(ad);
            return true;
        }).orElse(false);
    }

    @Override
    public List<ReservationDTO> getAllAdBookings(Long companyId) {
        return reservationRepository.findAllByCompanyId(companyId)
                .stream()
                .map(Reservation::getReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // Ajout de l'observateur (client)
            reservation.addObserver(reservation.getUser());

            if ("Accepter".equalsIgnoreCase(status)) {
                reservation.setReservationStatusWithNotify(ReservationStatus.APPROUVÉ);
            } else {
                reservation.setReservationStatusWithNotify(ReservationStatus.REJETÉ);
            }

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
}

