package com.example.ReservationService.dto;

import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class ServiceDTO {

    private Long id;

    private String serviceName;
    private String description;
    private Double price;

    private MultipartFile img;
    private byte[] returnedImg;

    private Long userId;
    private String adminName;

    // Méthode de création (GRASP Creator): creation de service
    public Ad toAd(User user) throws IOException {
        Ad ad = new Ad();
        ad.setServiceName(this.serviceName);
        ad.setDescription(this.description);
        ad.setPrice(this.price);
        if (this.img != null) {
            ad.setImg(this.img.getBytes());
        }
        ad.setUser(user);
        return ad;
    }
}
