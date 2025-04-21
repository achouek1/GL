package com.example.ReservationService.services.client;

import com.example.ReservationService.dto.AdDetailsForClientDTO;
import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.dto.ReviewDTO;
import com.example.ReservationService.dto.ServiceDTO;
import com.example.ReservationService.entity.Ad;

import java.util.List;

public interface ClientService {
     List<ServiceDTO> getAllServices();
     List<ServiceDTO> searchAdByName(String name);
     AdDetailsForClientDTO getAdDetailsByAdId(Long adId);


}
