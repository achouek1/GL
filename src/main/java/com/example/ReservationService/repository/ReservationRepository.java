package com.example.ReservationService.repository;

import com.example.ReservationService.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCompanyId(Long companyId);
    List<Reservation> findAllByUserId(Long userId);


    @Query
            ("SELECT r FROM Reservation r WHERE r.ad.id = :adId AND r.bookDate = :bookDate")
    List<Reservation> findByAdIdAndBookDate(@Param("adId") Long adId, @Param("bookDate") Date bookDate);

}
