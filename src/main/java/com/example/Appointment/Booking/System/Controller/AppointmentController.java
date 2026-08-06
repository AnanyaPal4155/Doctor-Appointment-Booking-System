package com.example.Appointment.Booking.System.Controller;

import com.example.Appointment.Booking.System.DTO.BookAppointmentRequestDto;
import com.example.Appointment.Booking.System.DTO.BookAppointmentResponseDto;
import com.example.Appointment.Booking.System.DTO.PatientHistoryDTO;
import com.example.Appointment.Booking.System.Entity.Appointment;
import com.example.Appointment.Booking.System.Entity.AvailableSlots;
import com.example.Appointment.Booking.System.Service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BookAppointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public BookAppointmentResponseDto bookAppointment(@RequestBody BookAppointmentRequestDto request)
    {
        return appointmentService.bookAppointment(request.getPatientId(),request.getSlotId());

    }

//    cancel appointment
    @PutMapping("/cancel/{appointmentId}")
    public String cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return "Appointment cancelled successfully";
    }

//    update appointment to new slot
    @PutMapping("/update")
    public String updateAppointment(
            @RequestParam Long appointmentId,
            @RequestParam Long newSlotId) {

        appointmentService.updateAppointment(appointmentId, newSlotId);
        return "Appointment updated successfully";
    }

//    appointment history
@GetMapping("/history")
public List<PatientHistoryDTO> history(HttpSession session) {
    Long patientId = (Long) session.getAttribute("patientId");
    if (patientId == null) {
        throw new RuntimeException("Not logged in");
    }
    return appointmentService.getPatientHistory(patientId);
}

    @GetMapping("/history/session")
    public List<PatientHistoryDTO> historySession(HttpSession session) {
        Long patientId = (Long) session.getAttribute("patientId");
        if (patientId == null) throw new RuntimeException("Not logged in");
        return appointmentService.getPatientHistory(patientId);
    }

}
