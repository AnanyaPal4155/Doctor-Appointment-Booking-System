package com.example.Appointment.Booking.System.Controller;

import com.example.Appointment.Booking.System.Entity.Appointment;
import com.example.Appointment.Booking.System.Entity.Patient;
import com.example.Appointment.Booking.System.Repository.DoctorRepository;
import com.example.Appointment.Booking.System.Repository.PatientRepository;
import com.example.Appointment.Booking.System.Service.AppointmentService;
import com.example.Appointment.Booking.System.Service.PatientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

@Autowired
private AppointmentService appointmentService;


    @GetMapping("/all")
    @ResponseBody
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // Search patients by name
    @GetMapping("/search")
    @ResponseBody
    public Patient searchPatientsByName(@RequestParam String name, @RequestParam String phoneNo) {
        Optional<Patient> patient = patientService.findPatientByNameAndPhoneNo(name,phoneNo);
        return patient.orElse(null);
    }
//    registration
    @PostMapping("/addPatient")
    public String addPatient(@ModelAttribute Patient patient,
                             HttpSession session) {

        // Save patient to database
        Patient savedPatient = patientService.registerPatient(patient);

        // Store patient id in session (auto-login)
        session.setAttribute("patientId", savedPatient.getId());

        // Redirect directly to dashboard
        return "redirect:/patient-dashboard.html";
    }

//    patient login
    @PostMapping("/login")
    public String patientLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        Patient patient = patientService.login(email, password);

        if (patient != null) {
            session.setAttribute("patientId", patient.getId());
            return "redirect:/patient-dashboard.html";
        }
        return "redirect:/login.html?error=true";
    }

    @GetMapping("/dashboard")
    public String patientDashboard() {
        return "patient-dashboard.html";
    }

//logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }

//    for booking appointment
    @GetMapping("/appointments/book")
    public String showBookAppointmentPage(HttpSession session) {
        Long patientId = (Long) session.getAttribute("patientId");
        if (patientId == null) {
            return "redirect:/login.html";  // patient not logged in
        }
        return "book-appointment.html"; // this is your static HTML page
    }
    @PostMapping("/appointments/book")
    public String bookAppointmentFromForm(@RequestParam Long slotId, HttpSession session) {

        Long patientId = (Long) session.getAttribute("patientId");
        if (patientId == null) {
            return "redirect:/login.html";
        }

        try {
            appointmentService.bookAppointment(patientId, slotId);
            return "redirect:/patient-dashboard.html?BOOKED=true";

        } catch (RuntimeException e) {
            // THIS LINE PREVENTS WHITE PAGE
            return "redirect:/patient-dashboard.html?error=" + e.getMessage();
        }
    }

    // ================= MY APPOINTMENTS PAGE DATA =================

    @GetMapping("/appointments/my")
    public String myAppointments(HttpSession session) {
        Long patientId = (Long) session.getAttribute("patientId");
        if (patientId == null) {
            return "redirect:/login.html";
        }
        return "my-appointments.html";
    }



}


