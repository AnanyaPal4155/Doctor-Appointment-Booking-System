package com.example.Appointment.Booking.System.Service;


public interface NotificationService {

    void sendEmail(String to, String subject, String body);
}
