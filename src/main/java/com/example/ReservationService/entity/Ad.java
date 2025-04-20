package com.example.ReservationService.entity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.ReservationService.dto.ServiceDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="services")
@Data

public class Ad {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    private String description;
    private Double price;
    @Lob
    @Column(columnDefinition="longblob")
    private byte[]img;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="user_id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    private User user;

    public ServiceDTO getServiceDTO() {
        ServiceDTO serviceDTO=new ServiceDTO();
        serviceDTO.setId(id);
        serviceDTO.setServiceName(serviceName);
        serviceDTO.setDescription(description);
        serviceDTO.setPrice(price);
        serviceDTO.setAdminName(user.getName());
        serviceDTO.setReturnedImg(img);
      //  serviceDTO.setUserId(user.getId());
        //serviceDTO.set


        return serviceDTO;
    }


}

