package com.example.ReservationService.controller;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.dto.ServiceDTO;
import com.example.ReservationService.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
private CompanyService companyService;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postService(@PathVariable Long userId , @ModelAttribute ServiceDTO serviceDTO) throws IOException {
        boolean suc = companyService.postService(userId, serviceDTO);
        if (suc) {
            return ResponseEntity. status(HttpStatus.OK).build();

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

@GetMapping("/ads/{userId}")
    public ResponseEntity<?> getAllServicesByUserId(@PathVariable Long userId ) {
return ResponseEntity.ok(companyService.getAllServices(userId));
}

@GetMapping("/ad/{adId}")
public ResponseEntity<?> getAdById(@PathVariable Long adId ){
        ServiceDTO serviceDTO = companyService.getAdById(adId);
        if (serviceDTO != null) {
            return ResponseEntity.ok(serviceDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
}

    @PutMapping("/ad/{adId}")
    public ResponseEntity<?> updateAd(@PathVariable Long adId , @ModelAttribute ServiceDTO serviceDTO) throws IOException {
        boolean suc = companyService.updateAd(adId, serviceDTO);
        if (suc) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId )  {
        boolean suc = companyService.deleteAd(adId);
        if (suc) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bookings/{companyId}")
    public ResponseEntity<List<ReservationDTO>> getAllAdBookings(@PathVariable Long companyId)  {
        return ResponseEntity.ok(companyService.getAllAdBookings(companyId));
    }

    @GetMapping("/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId , @PathVariable String status) {
        boolean suc = companyService.changeBookingStatus(bookingId, status);
        if (suc) return ResponseEntity.status(HttpStatus.OK).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }




}
