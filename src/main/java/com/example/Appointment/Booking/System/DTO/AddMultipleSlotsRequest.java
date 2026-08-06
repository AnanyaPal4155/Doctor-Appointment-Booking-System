package com.example.Appointment.Booking.System.DTO;

import java.time.LocalDate;
import java.util.List;

public class AddMultipleSlotsRequest {

    private Long doctorId;
    private LocalDate date;
    private List<String> time;
    private List<Integer> capacities;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Integer> getCapacities() {
        return capacities;
    }

    public void setCapacities(List<Integer> capacities) {
        this.capacities = capacities;
    }
}







