package com.example.Appointment.Booking.System.Service;

import com.example.Appointment.Booking.System.Entity.Doctor;
import com.example.Appointment.Booking.System.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepo;

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public List<Doctor> searchBySpecialization(String specialization) {
        return doctorRepo.findBySpecializationIgnoreCase(specialization);
    }
}