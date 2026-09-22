package com.example.Appointment.Booking.System.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private int experience;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Appointment> doctorAppointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<AvailableSlots> doctorSlots;


    public Doctor()
    {

    }

    public Doctor(Long doctorId, String name, String specialization, int experience) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorIdd) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<AvailableSlots> getDoctorSlots() {
        return doctorSlots;
    }

    public void setDoctorSlots(List<AvailableSlots> doctorSlots) {
        this.doctorSlots = doctorSlots;
    }
    public List<Appointment> getDoctorAppointments() {
        return doctorAppointments;
    }

    public void setDoctorAppointments(List<Appointment> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }

}