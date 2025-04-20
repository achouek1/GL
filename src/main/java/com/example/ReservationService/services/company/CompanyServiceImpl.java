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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    public boolean postService(Long userId, ServiceDTO serviceDTO) throws IOException {
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
    public List<ServiceDTO> getAllServices(Long userId) {
        return serviceRepository.findAllByUserId(userId).stream().map(Ad :: getServiceDTO).collect(Collectors.toList());
    }

    public ServiceDTO getAdById(Long adId) {
        Optional<Ad> optionalAd = serviceRepository.findById(adId);
        if (optionalAd.isPresent()) {
            return optionalAd.get().getServiceDTO();
        }
        return null;
    }



    public boolean updateAd (Long adId, ServiceDTO serviceDTO) throws IOException {
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
        }else{
            return false;
        }
    }

    public boolean deleteAd(Long adId) {
        Optional<Ad> optionalAd = serviceRepository.findById(adId);
        if (optionalAd.isPresent()) {
            serviceRepository.delete(optionalAd.get());
            return true;
        }
        return false;
    }

    public List<ReservationDTO> getAllAdBookings(Long companyId) {
        return reservationRepository.findAllByCompanyId(companyId)
                .stream().map(Reservation::getReservationDTO).collect(Collectors.toList());
    }

    public boolean changeBookingStatus(Long bookingId, String status){
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if (optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            if(Objects.equals(status,"Accepter")){
                existingReservation.setReservationStatus(ReservationStatus.APPROUVÉ);

            }else{
                existingReservation.setReservationStatus(ReservationStatus.REJETÉ);
            }
            reservationRepository.save(existingReservation);
            return true;
        }
        return false;
    }




}

