package com.example.Appointment.Booking.System.Service;

import com.example.Appointment.Booking.System.DTO.Admin.AppointmentAdminDTO;
import com.example.Appointment.Booking.System.DTO.BookAppointmentResponseDto;
import com.example.Appointment.Booking.System.DTO.PatientHistoryDTO;
import com.example.Appointment.Booking.System.Entity.Appointment;
import com.example.Appointment.Booking.System.Entity.AvailableSlots;
import com.example.Appointment.Booking.System.Entity.Patient;
import com.example.Appointment.Booking.System.Repository.AppointmentRepository;
import com.example.Appointment.Booking.System.Repository.AvailableSlotsRepository;
import com.example.Appointment.Booking.System.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AvailableSlotsRepository availableSlotsRepo;

    @Autowired
    private NotificationService notificationService;

   @Override
    public BookAppointmentResponseDto bookAppointment(Long patientId,Long slotId)
   {
       Patient patient = patientRepo.findById(patientId).orElseThrow(()-> new RuntimeException("no patient found"));
       AvailableSlots slot = availableSlotsRepo.findById(slotId).orElseThrow(()-> new RuntimeException("no slot found"));

//check is patient already has booked appointment.
       if (appointmentRepo.existsByPatient_IdAndStatus(patientId, "BOOKED")) {
           throw new RuntimeException("Patient already has an active appointment");
       }
//       check availability
       if(slot.getBookedCount() >= slot.getCapacity())
       {
           throw new RuntimeException("Slot is full");
      }
// Increase bookedCount
       slot.setBookedCount(slot.getBookedCount()+1);
       availableSlotsRepo.save(slot);

//       create and save appointment
       Appointment appointment = new Appointment();
       appointment.setPatient(patient);
       appointment.setDoctor(slot.getDoctor());
       appointment.setSlots(slot);
       appointment.setStatus("BOOKED");

       Appointment saved = appointmentRepo.save(appointment);

       // Send email to patient
       String subject = "Appointment Booked Successfully";
       String body = "Hello " + patient.getName() + ",\n\n" +
               "Your appointment with Dr. " + slot.getDoctor().getName() +
               " is confirmed.\n" +
               "Date: " + slot.getDate() + "\n" +
               "Time: " + slot.getTime() + "\n\n" +
               "Thank you!";
       notificationService.sendEmail(patient.getEmail(), subject, body);

//       return bookingresponse

       return new BookAppointmentResponseDto(

               saved.getAppointmentId(),
               patient.getName(),
               slot.getDate(),
               slot.getTime(),
               saved.getStatus()
       );

}
//cancel appointment
    public void cancelAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepo
                .findByAppointmentIdAndStatus(appointmentId, "BOOKED")
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Change status
        appointment.setStatus("CANCELLED");

        //  Free slot capacity
        AvailableSlots slot = appointment.getSlots();

        if (slot.getBookedCount() > 0) {
            slot.setBookedCount(slot.getBookedCount() - 1);
        }

        appointmentRepo.save(appointment);
        availableSlotsRepo.save(slot);

        // Send email to patient
        String subject = "Appointment Canceled";
        String body = "Hello " + appointment.getPatient().getName() + ",\n\n" +
                "Your appointment with Dr. " + appointment.getDoctor().getName() +
                " on " + appointment.getSlots().getDate() + " at " +
                appointment.getSlots().getTime() + " has been canceled.\n\n" +
                "Thank you!";
        notificationService.sendEmail(appointment.getPatient().getEmail(), subject, body);
    }

//    update appointment
@Override
public void updateAppointment(Long appointmentId, Long newSlotId) {

    Appointment appointment = appointmentRepo
            .findByAppointmentIdAndStatus(appointmentId, "BOOKED")
            .orElseThrow(() -> new RuntimeException("Appointment not found"));

    AvailableSlots oldSlot = appointment.getSlots();

    AvailableSlots newSlot = availableSlotsRepo.findById(newSlotId)
            .orElseThrow(() -> new RuntimeException("New slot not found"));

    if (oldSlot.getSlotId().equals(newSlotId)) {
        throw new RuntimeException("New slot cannot be same as old slot");
    }

    // Check capacity
    if (newSlot.getBookedCount() >= newSlot.getCapacity()) {
        throw new RuntimeException("Slot is full");
    }

    // Update slot counts
    oldSlot.setBookedCount(oldSlot.getBookedCount() - 1);
    newSlot.setBookedCount(newSlot.getBookedCount() + 1);

    // Update appointment slot
    appointment.setSlots(newSlot);

    availableSlotsRepo.save(oldSlot);
    availableSlotsRepo.save(newSlot);
    appointmentRepo.save(appointment);

    // Send email to patient
    String subject = "Appointment Updated Successfully";
    String body = "Hello " + appointment.getPatient().getName() + ",\n\n" +
            "Your appointment with Dr. " + appointment.getDoctor().getName() +
            " has been updated.\n" +
            "New Date: " + newSlot.getDate() + "\n" +
            "New Time: " + newSlot.getTime() + "\n\n" +
            "Thank you!";
    notificationService.sendEmail(appointment.getPatient().getEmail(), subject, body);
}
//get appointment history using patientId
@Override
public List<PatientHistoryDTO> getPatientHistory(Long patientId) {

    List<Appointment> appointments =
            appointmentRepo.findByPatientId(patientId);

    List<PatientHistoryDTO> response = new ArrayList<>();

    for (Appointment a : appointments) {

        PatientHistoryDTO dto = new PatientHistoryDTO();
        dto.setAppointmentId(a.getAppointmentId());
        dto.setDoctorName(a.getDoctor().getName());
        dto.setDate(a.getSlots().getDate().toString());
        dto.setTime(a.getSlots().getTime());
        dto.setStatus(a.getStatus());

        response.add(dto);
    }

    return response;
}

    public List<AppointmentAdminDTO> getAllAppointmentsForAdmin() {

        List<Appointment> appointments = appointmentRepo.findAll();
        List<AppointmentAdminDTO> response = new ArrayList<>();

        for (Appointment a : appointments) {
            AppointmentAdminDTO dto = new AppointmentAdminDTO();
            dto.setAppointmentId(a.getAppointmentId());
            dto.setPatientName(a.getPatient().getName());
            dto.setPatientPhone(a.getPatient().getPhoneNo());
            dto.setDoctorName(a.getDoctor().getName());
            dto.setDate(a.getSlots().getDate().toString());
            dto.setTime(a.getSlots().getTime());
            dto.setStatus(a.getStatus());
            response.add(dto);
        }
        return response;
    }

    public long getBookedCount() {
        return appointmentRepo.countByStatus("Booked");
    }

    public long getCancelledCount() {
        return appointmentRepo.countByStatus("CANCELLED");
    }

    public List<Appointment> getMyAppointments(Long patientId) {
        return appointmentRepo.findByPatient_Id(patientId);
    }




}
