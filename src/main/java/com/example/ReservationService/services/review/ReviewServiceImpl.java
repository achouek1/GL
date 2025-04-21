package com.example.ReservationService.services.review;

import com.example.ReservationService.dto.ReviewDTO;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.entity.Review;
import com.example.ReservationService.entity.User;
import com.example.ReservationService.enums.ReviewStatus;
import com.example.ReservationService.repository.ReservationRepository;
import com.example.ReservationService.repository.ReviewRepository;
import com.example.ReservationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public boolean giveReview(ReviewDTO reviewDTO) {
        Optional<User> optionalUser= userRepository.findById(reviewDTO.getUserId());
        Optional <Reservation> optionalBooking= reservationRepository.findById(reviewDTO.getBookId());
        if(optionalUser.isPresent() && optionalBooking.isPresent()){
            // Utilisation du Creator via DTO
            Review review = reviewDTO.toReview(optionalUser.get(), optionalBooking.get().getAd());

            reviewRepository.save(review);
            Reservation booking= optionalBooking.get();
            booking.setReviewStatus(ReviewStatus.VRAI);


            reservationRepository.save(booking);
            return true;
        }
        return false;
    }
}