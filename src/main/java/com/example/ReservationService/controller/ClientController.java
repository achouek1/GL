package com.example.ReservationService.controller;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.dto.ReviewDTO;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/ads")
    public ResponseEntity<?> getAllServices(){
        return ResponseEntity.ok(clientService.getAllServices());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchAdByService(@PathVariable String name){
        return ResponseEntity.ok(clientService.searchAdByName(name));
    }

    @PostMapping("/book-service")
    public ResponseEntity<?> bookService(@RequestBody ReservationDTO reservationDTO){

        boolean suc= clientService.bookService(reservationDTO);
        if(suc){
            return ResponseEntity.status(HttpStatus.OK).build();

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        
    }


    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdDetailsByAdId(@PathVariable Long adId){
        return ResponseEntity.ok(clientService.getAdDetailsByAdId(adId));

    }

    @GetMapping("/my-bookings/{userId}")
    public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(clientService.getAllBookingsByUserId(userId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@RequestBody ReviewDTO reviewDTO){
        Boolean suc= clientService.giveReview(reviewDTO);
        if(suc){
            return ResponseEntity.status(HttpStatus.OK).build();

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
