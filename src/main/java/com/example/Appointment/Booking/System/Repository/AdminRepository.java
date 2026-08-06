package com.example.Appointment.Booking.System.Repository;

import com.example.Appointment.Booking.System.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findByEmail(String email);
}
