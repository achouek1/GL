package com.example.ReservationService.utill; // Même package que JwtUtil

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {

    @Test
    public void testSingletonInstance() {
        JwtUtil instance1 = JwtUtil.getInstance();
        JwtUtil instance2 = JwtUtil.getInstance();

        // Vérifie que les deux instances sont identiques
        assertSame(instance1, instance2, "Les instances doivent être les mêmes");
    }
}