package com.example.Appointment.Booking.System.Service;

import com.example.Appointment.Booking.System.Entity.AvailableSlots;
import com.example.Appointment.Booking.System.Entity.Doctor;
import com.example.Appointment.Booking.System.Repository.AvailableSlotsRepository;
import com.example.Appointment.Booking.System.Repository.DoctorRepository;

import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {


    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
   private AvailableSlotsRepository availableSlotsRepo;

    // Only one doctor in system
    private Doctor getDoctor() {
        return doctorRepo.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found. Please add doctor first"));
    }

    @Override
    public AvailableSlots addSlot(LocalDate date, String time, int capacity) {

        AvailableSlots slot = new AvailableSlots();
        slot.setDate(date);
        slot.setTime(time);
        slot.setCapacity(capacity);
        slot.setBookedCount(0);
        slot.setDoctor(getDoctor());

        return availableSlotsRepo.save(slot);
    }

    @Override
    public List<AvailableSlots> getAllSlots() {
        return availableSlotsRepo.findByActiveTrue();
    }


    @Override
    public List<AvailableSlots> getFreeSlots() {
       return availableSlotsRepo.findFreeSlots();
    }


    @Override
    public void deleteSlot(Long slotId) {

        AvailableSlots slot = availableSlotsRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.getBookedCount() > 0) {
            throw new RuntimeException("Cannot delete slot with bookings");
        }

        slot.setActive(false);   // MAIN LINE
        availableSlotsRepo.save(slot);
    }


}
