package com.example.Appointment.Booking.System.Controller;

import com.example.Appointment.Booking.System.DTO.Admin.AppointmentAdminDTO;
import com.example.Appointment.Booking.System.Entity.Admin;
import com.example.Appointment.Booking.System.Service.AdminService;
import com.example.Appointment.Booking.System.Service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AppointmentService appointmentService;

    // Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "admin-login.html";
    }

    // Login Logic
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        try {
            Admin admin = adminService.login(email, password);
            session.setAttribute("adminId", admin.getId());
            return "redirect:/admin-dashboard.html";

        } catch (RuntimeException e) {

            if (e.getMessage().equals("EMAIL_NOT_FOUND")) {
                return "redirect:/admin/login?emailError=true";
            }

            if (e.getMessage().equals("WRONG_PASSWORD")) {
                return "redirect:/admin/login?passwordError=true";
            }

            return "redirect:/admin/login?error=true";
        }
    }

//    // Dashboard
//    @GetMapping("/dashboard")
//    public String dashboard(HttpSession session) {
//        if (session.getAttribute("adminId") == null) {
//            return "redirect:/admin/login";
//        }
//        return "admin-dashboard.html";
//    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }

    @GetMapping("/appointments")
    @ResponseBody
    public List<AppointmentAdminDTO> getAllAppointments(HttpSession session) {

        if (session.getAttribute("adminId") == null) {
            throw new RuntimeException("Unauthorized");
        }


        return appointmentService.getAllAppointmentsForAdmin();
    }
    // Get total booked appointments
    @GetMapping("/bookedCount")
    @ResponseBody
    public long getBookedCount(HttpSession session) {
        if (session.getAttribute("adminId") == null) throw new RuntimeException("Unauthorized");
        return appointmentService.getBookedCount();
    }

    // Get total cancelled appointments
    @GetMapping("/cancelledCount")
    @ResponseBody
    public long getCancelledCount(HttpSession session) {
        if (session.getAttribute("adminId") == null) throw new RuntimeException("Unauthorized");
        return appointmentService.getCancelledCount();
    }

}
