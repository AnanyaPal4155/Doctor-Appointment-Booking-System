package com.example.Appointment.Booking.System.DTO.Admin;

public class AdminDashboardDTO {

    private long totalPatients;
    private long totalAppointments;
    private long bookedAppointments;
    private long cancelledAppointments;
    private long totalSlots;

    public long getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(long totalPatients) {
        this.totalPatients = totalPatients;
    }

    public long getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(long totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public long getBookedAppointments() {
        return bookedAppointments;
    }

    public void setBookedAppointments(long bookedAppointments) {
        this.bookedAppointments = bookedAppointments;
    }

    public long getCancelledAppointments() {
        return cancelledAppointments;
    }

    public void setCancelledAppointments(long cancelledAppointments) {
        this.cancelledAppointments = cancelledAppointments;
    }

    public long getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(long totalSlots) {
        this.totalSlots = totalSlots;
    }
}
