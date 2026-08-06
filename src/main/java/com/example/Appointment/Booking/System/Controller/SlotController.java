package com.example.Appointment.Booking.System.Controller;

import com.example.Appointment.Booking.System.Entity.AvailableSlots;
import com.example.Appointment.Booking.System.Service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/slot")
public class SlotController {

    @Autowired
    private SlotService slotService;

    // Admin adds slot one by one
    @PostMapping("/add")
    public AvailableSlots addSlot(
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam int capacity) {

        return slotService.addSlot(
                LocalDate.parse(date),
                time,
                capacity
        );
    }

    // Admin dashboard – view all slots
    @GetMapping("/all")
    public List<AvailableSlots> getAllSlots() {
        return slotService.getAllSlots();
    }

    // For booking / reschedule
    @GetMapping("/freeSlots")
    public List<AvailableSlots> getFreeSlots() {
        return slotService.getFreeSlots();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSlot(@PathVariable Long id) {
        slotService.deleteSlot(id);
    }
}
