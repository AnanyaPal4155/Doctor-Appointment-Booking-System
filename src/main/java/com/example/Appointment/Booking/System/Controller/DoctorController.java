package com.example.Appointment.Booking.System.Controller;

import com.example.Appointment.Booking.System.Entity.Doctor;
import com.example.Appointment.Booking.System.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Get all doctors
    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Search doctors by specialization
    @GetMapping("/search")
    public List<Doctor> searchDoctors(@RequestParam String specialization) {
        return doctorService.searchBySpecialization(specialization);
    }
}