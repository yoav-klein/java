package org.example;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class HotelRoom {
    public void createReservation(@Min(1) int duration, @NotNull User user) {
        
    }
}
