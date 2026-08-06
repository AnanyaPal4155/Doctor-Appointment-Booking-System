package com.example.Appointment.Booking.System.DTO;

import java.time.LocalDate;

public class BookAppointmentResponseDto {

    private Long AppointmentId;
//    private Long patientId;
//    private Long slotId;
    private String patientName;
    private LocalDate bookingDate;
    private String bookingTime;
    private String BookingStatus;

    public BookAppointmentResponseDto() {
    }

    public BookAppointmentResponseDto(Long appointmentId, String patientName, LocalDate bookingDate, String bookingTime, String bookingStatus) {
        AppointmentId = appointmentId;
        this.patientName = patientName;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        BookingStatus = bookingStatus;
    }

    public Long getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        AppointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getBookingStatus() {
        return BookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        BookingStatus = bookingStatus;
    }
}
