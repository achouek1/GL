package com.example.ReservationService.services.client;

import com.example.ReservationService.dto.AdDetailsForClientDTO;
import com.example.ReservationService.dto.ServiceDTO;
import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.Review;
import com.example.ReservationService.repository.ReviewRepository;
import com.example.ReservationService.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll().stream().map(Ad::getServiceDTO).collect(Collectors.toList());
    }

    public List<ServiceDTO> searchAdByName(String name){
        return serviceRepository.findAllByServiceNameContaining(name).stream().map(Ad::getServiceDTO).collect(Collectors.toList());
    }


public AdDetailsForClientDTO getAdDetailsByAdId(Long adId) {
        Optional<Ad> optionalAd = serviceRepository.findById(adId);

        AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
        if(optionalAd.isPresent()){
            adDetailsForClientDTO.setServiceDTO(optionalAd.get().getServiceDTO());
        List<Review> reviewList= reviewRepository.findAllByAdId(adId);
        adDetailsForClientDTO.setReviewDTOList(reviewList.stream().map(Review::getDTO).collect(Collectors.toList()));

        }
        return adDetailsForClientDTO;
}
}
