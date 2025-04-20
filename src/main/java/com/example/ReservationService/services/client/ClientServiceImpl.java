package com.example.ReservationService.services.client;

import com.example.ReservationService.dto.AdDetailsForClientDTO;
import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.dto.ReviewDTO;
import com.example.ReservationService.dto.ServiceDTO;
import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.entity.Review;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.enums.ReservationStatus;
import com.example.ReservationService.enums.ReviewStatus;
import com.example.ReservationService.repository.ReservationRepository;
import com.example.ReservationService.repository.ReviewRepository;
import com.example.ReservationService.repository.ServiceRepository;
import com.example.ReservationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll().stream().map(Ad::getServiceDTO).collect(Collectors.toList());
    }

    public List<ServiceDTO> searchAdByName(String name){
        return serviceRepository.findAllByServiceNameContaining(name).stream().map(Ad::getServiceDTO).collect(Collectors.toList());
    }

public boolean bookService(ReservationDTO reservationDTO) {
    Optional<Ad> optionalAd = serviceRepository.findById(reservationDTO.getAdId());
    Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

    if(optionalAd.isPresent() && optionalUser.isPresent()){
        Reservation reservation = new Reservation();

         reservation.setBookDate(reservationDTO.getBookDate());
         reservation.setReservationStatus(ReservationStatus.ENATTENTE);
         reservation.setUser(optionalUser.get());

         reservation.setAd(optionalAd.get());
         reservation.setCompany(optionalAd.get().getUser());
         reservation.setReviewStatus(ReviewStatus.FAUX);

         reservationRepository.save(reservation);
         return true;
    }
    return false;
}

public AdDetailsForClientDTO getAdDetailsByAdId(Long adId) {
        Optional<Ad> optionalAd = serviceRepository.findById(adId);

        AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
        if(optionalAd.isPresent()){
            adDetailsForClientDTO.setServiceDTO(optionalAd.get().getServiceDTO());
        List<Review> reviewList= reviewRepository.findAllByAdId(adId);
        adDetailsForClientDTO.setReviewDTOList(reviewList.stream().map(Review::getDTO).collect(Collectors.toList()));

        }
        return adDetailsForClientDTO;
}

public List<ReservationDTO> getAllBookingsByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDTO).collect(Collectors.toList());
    }

public boolean giveReview(ReviewDTO reviewDTO){
        Optional <User> optionalUser= userRepository.findById(reviewDTO.getUserId());
        Optional <Reservation> optionalBooking= reservationRepository.findById(reviewDTO.getBookId());
        if(optionalUser.isPresent() && optionalBooking.isPresent()){
            Review review = new Review();

            review.setReviewDate(new Date());
            review.setReview(reviewDTO.getReview());
            review.setRating(reviewDTO.getRating());
            review.setUser(optionalUser.get());
            review.setAd(optionalBooking.get().getAd());

            reviewRepository.save(review);
            Reservation booking= optionalBooking.get();
            booking.setReviewStatus(ReviewStatus.VRAI);


            reservationRepository.save(booking);
            return true;
        }
        return false;
}

}
