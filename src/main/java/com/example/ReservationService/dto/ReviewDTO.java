package com.example.ReservationService.dto;

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
}
