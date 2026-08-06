package com.example.Appointment.Booking.System.Service;

import com.example.Appointment.Booking.System.DTO.Admin.AppointmentAdminDTO;
import com.example.Appointment.Booking.System.DTO.BookAppointmentResponseDto;
import com.example.Appointment.Booking.System.DTO.PatientHistoryDTO;
import com.example.Appointment.Booking.System.Entity.Appointment;

import java.util.List;

public interface AppointmentService {

    BookAppointmentResponseDto bookAppointment(Long patientId,Long slotId);
    void cancelAppointment(Long appointmentId);
    void updateAppointment(Long appointmentId, Long newSlotId);
    List<PatientHistoryDTO> getPatientHistory(Long patientId);

    List<AppointmentAdminDTO> getAllAppointmentsForAdmin();

    long getBookedCount();

    long getCancelledCount();

//    List<Appointment> getMyAppointments(Long patientId);
//    List<Appointment> getAllAppointmentsForAdmin();



//    List<BookAppointmentResponseDto> getAppointmentByDate(String date);
}
