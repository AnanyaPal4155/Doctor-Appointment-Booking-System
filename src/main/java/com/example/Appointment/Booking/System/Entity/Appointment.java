package com.example.Appointment.Booking.System.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonBackReference
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "availableSlotId")
    private AvailableSlots slots;

    @Column(nullable = false)
    private LocalDateTime bookingTime = LocalDateTime.now();

    @Column(nullable = false)
    private String status = "booked";

    public Appointment()
    {

    }
    public Appointment(Long appointmentId, Patient patient, Doctor doctor, AvailableSlots slots, LocalDateTime bookingTime, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.slots = slots;
        this.bookingTime = bookingTime;
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public AvailableSlots getSlots() {
        return slots;
    }

    public void setSlots(AvailableSlots slots) {
        this.slots = slots;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
