package com.example.Appointment.Booking.System.Repository;


import com.example.Appointment.Booking.System.Entity.AvailableSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

    @Repository
    public interface AvailableSlotsRepository extends JpaRepository<AvailableSlots,Long> {

        @Query("SELECT s FROM AvailableSlots s WHERE s.bookedCount < s.capacity")
        List<AvailableSlots> findFreeSlots();

        List<AvailableSlots> findByActiveTrue();
    }





