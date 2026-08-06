package com.example.Appointment.Booking.System.Repository;

import com.example.Appointment.Booking.System.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
