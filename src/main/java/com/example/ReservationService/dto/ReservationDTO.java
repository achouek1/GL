package com.example.ReservationService.dto;
import jakarta.validation.constraints.PastOrPresent;

import com.example.ReservationService.enums.ReservationStatus;
import com.example.ReservationService.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    private Long id;
    @PastOrPresent(message = "La date de réservation ne peut pas être future")
    private Date bookDate;
    private String serviceName;
 private ReservationStatus reservationStatus;
 private ReviewStatus reviewStatus;
 private Long userId;
 private String userName;
    private Long companyId;
    private Long adId;


}
