package com.example.ReservationService.repository;

import com.example.ReservationService.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Ad,Long> {
List<Ad> findAllByUserId (Long userId);

List<Ad> findAllByServiceNameContaining (String name);
}
