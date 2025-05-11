package com.example.ReservationService.factory;

import com.example.ReservationService.dto.ServiceDTO;
import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.User;

public class AdFactory {

    public static Ad createAdFromDTO(ServiceDTO serviceDTO, User user) {
        Ad ad = new Ad();
        ad.setServiceName(serviceDTO.getServiceName());
        ad.setDescription(serviceDTO.getDescription());
        ad.setPrice(serviceDTO.getPrice());
        ad.setUser(user);
        return ad;
    }
}

