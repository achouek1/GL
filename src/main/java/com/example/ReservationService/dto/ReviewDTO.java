package com.example.ReservationService.dto;

import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.Review;
import com.example.ReservationService.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private long id;
    private Date reviewDate;
    private String review;
    private Long rating;

    private Long userId;
    private Long adId;

    private String clientName;
    private String serviceName;
    private Long bookId;

    // Méthode de création (GRASP Creator):creation avis
    public Review toReview(User user, Ad ad) {
        Review review = new Review();
        review.setReviewDate(new Date());
        review.setReview(this.review);
        review.setRating(this.rating);
        review.setUser(user);
        review.setAd(ad);
        return review;
    }
}
