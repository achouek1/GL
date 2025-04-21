package com.example.ReservationService.controller;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.dto.ReviewDTO;
import com.example.ReservationService.entity.Reservation;
import com.example.ReservationService.services.booking.BookingService;
import com.example.ReservationService.services.client.ClientService;
import com.example.ReservationService.services.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private BookingService bookingService;  // SRP respecté

    @Autowired
    private ReviewService reviewService;    // SRP respecté
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
    public ResponseEntity<?> bookService(@RequestBody ReservationDTO reservationDTO) {
        boolean success = bookingService.bookService(reservationDTO);
        return success
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdDetailsByAdId(@PathVariable Long adId){
        return ResponseEntity.ok(clientService.getAdDetailsByAdId(adId));

    }

    @GetMapping("/my-bookings/{userId}")
    public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(bookingService.getAllBookingsByUserId(userId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@RequestBody ReviewDTO reviewDTO) {
        boolean success = reviewService.giveReview(reviewDTO);
        return success
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
