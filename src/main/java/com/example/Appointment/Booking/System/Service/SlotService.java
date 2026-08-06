package com.example.Appointment.Booking.System.Service;

import com.example.Appointment.Booking.System.Entity.AvailableSlots;

import java.time.LocalDate;
import java.util.List;

public interface SlotService {

    AvailableSlots addSlot(LocalDate date, String time, int capacity);

    List<AvailableSlots> getAllSlots();

    List<AvailableSlots> getFreeSlots();

    void deleteSlot(Long slotId);
}
