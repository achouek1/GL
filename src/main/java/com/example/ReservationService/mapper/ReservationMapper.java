package com.example.ReservationService.mapper;

import com.example.ReservationService.dto.ReservationDTO;
import com.example.ReservationService.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "adId", source = "ad.id")
    ReservationDTO toDTO(Reservation entity);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "company.id", source = "companyId")
    @Mapping(target = "ad.id", source = "adId")
    Reservation toEntity(ReservationDTO dto);
}