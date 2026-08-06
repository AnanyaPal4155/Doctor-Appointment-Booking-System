package com.example.Appointment.Booking.System.Service;

import com.example.Appointment.Booking.System.Entity.Admin;
import com.example.Appointment.Booking.System.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Override
    public Admin login(String email, String password) {

        Admin admin = adminRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("EMAIL_NOT_FOUND"));

        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("WRONG_PASSWORD");
        }

        return admin;
    }
}
