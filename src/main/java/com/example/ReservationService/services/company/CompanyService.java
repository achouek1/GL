package com.example.ReservationService.services.company;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.dto.ServiceDTO;

import java.io.IOException;
import java.util.List;

public interface CompanyService
{
 boolean   postService(Long userId, ServiceDTO serviceDTO) throws IOException;
 List<ServiceDTO> getAllServices(Long userId);
 ServiceDTO getAdById(Long adId);
 boolean updateAd (Long adId, ServiceDTO serviceDTO) throws IOException;
 boolean deleteAd(Long adId);
 List<ReservationDTO> getAllAdBookings(Long companyId);
 boolean changeBookingStatus(Long bookingId, String status);


}
