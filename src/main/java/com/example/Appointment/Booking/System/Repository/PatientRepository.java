package com.example.Appointment.Booking.System.Repository;

import com.example.Appointment.Booking.System.Entity.Appointment;
import com.example.Appointment.Booking.System.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
//    List<Patient> findByNameContainingIgnoreCase(String name);

    Optional<Patient> findByNameAndPhoneNo(String name, String phoneNo);
    Optional<Patient> findByEmailAndPassword(String email, String password);


}
