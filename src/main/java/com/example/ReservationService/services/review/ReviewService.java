package com.example.ReservationService.services.review;

import com.example.ReservationService.dto.ReviewDTO;

public interface ReviewService {
    boolean giveReview(ReviewDTO reviewDTO);
}