package com.example.ReservationService.dto;

import com.example.ReservationService.entity.Ad;
import com.example.ReservationService.entity.User;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceDTOTest {

    @Test
    public void testServiceDTOCreation() throws IOException {
        ServiceDTO dto = new ServiceDTO();
        dto.setServiceName("Service Test");
        dto.setDescription("Description");
        dto.setPrice(100.0);

        User user = new User();
        Ad ad = dto.toAd(user);

        assertEquals("Service Test", ad.getServiceName());
        assertEquals(user, ad.getUser());
    }
}
