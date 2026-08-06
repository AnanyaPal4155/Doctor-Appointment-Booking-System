package com.example.Appointment.Booking.System.Entity;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="slots")
public class AvailableSlots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;
    private LocalDate date;
    private String time;
    private int capacity;
    private int bookedCount;

    @Column(nullable = false)
    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ManyToOne
    @JoinColumn(name = "doctorId")
    @JsonBackReference
    private Doctor doctor;

    public AvailableSlots()
    {

    }

    public AvailableSlots(Long slotId, LocalDate date, String time, int capacity) {
        this.slotId = slotId;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getBookedCount() { return bookedCount; }
    public void setBookedCount(int bookedCount) { this.bookedCount = bookedCount; }

    public boolean isBookedStatus() {
        return bookedCount >= capacity; // true if slot full
    }

}
