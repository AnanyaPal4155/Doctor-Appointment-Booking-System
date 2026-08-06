package com.example.Appointment.Booking.System.Repository;

import com.example.Appointment.Booking.System.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    boolean existsByPatient_IdAndStatus(Long patientId, String status);
    Optional<Appointment> findByAppointmentIdAndStatus(Long appointmentId, String status);

//    List<Appointment> findByPatientId(Long patientId);

    long countByStatus(String status);

    List<Appointment> findByPatient_Id(Long patientId);

    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findAllByOrderByAppointmentIdDesc();

}
