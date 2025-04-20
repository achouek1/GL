package com.example.ReservationService.dto;

import lombok.Data;

import java.util.List;

@Data

public class AdDetailsForClientDTO {
private ServiceDTO serviceDTO;
private List<ReviewDTO> reviewDTOList;


}
