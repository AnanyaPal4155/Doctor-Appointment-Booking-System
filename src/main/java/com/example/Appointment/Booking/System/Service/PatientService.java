package com.example.Appointment.Booking.System.Service;

import com.example.Appointment.Booking.System.DTO.Admin.PatientAdminDTO;
import com.example.Appointment.Booking.System.Entity.Patient;
import com.example.Appointment.Booking.System.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Patient registerPatient(Patient patient) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(patient.getPassword());
        patient.setPassword(hashedPassword);
        return patientRepo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }
    public Patient getPatientById(Long patientId) {
        return patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // Search by name and phone no.
    public Optional<Patient> findPatientByNameAndPhoneNo(String name, String phoneNo) {
        return patientRepo.findByNameAndPhoneNo(name,phoneNo);
    }

    public List<PatientAdminDTO> getAllPatientsForAdmin() {
        List<Patient> patients = patientRepo.findAll();
        List<PatientAdminDTO> response = new ArrayList<>();

        for (Patient p : patients) {
            PatientAdminDTO dto = new PatientAdminDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setPhoneNo(p.getPhoneNo());
            dto.setEmail(p.getEmail());
            dto.setAge(p.getAge());
            response.add(dto);
        }
        return response;
    }

    public long getTotalPatientsCount() {
        return patientRepo.count();
    }

    //    login
    public Patient login(String email, String password) {
        Optional<Patient> patientOpt = patientRepo.findByEmail(email);

        if (patientOpt.isEmpty()) {
            return null;
        }

        Patient patient = patientOpt.get();

        // Compare plain password with hashed password
        if (passwordEncoder.matches(password, patient.getPassword())) {
            return patient;
        }

        return null;
    }


}