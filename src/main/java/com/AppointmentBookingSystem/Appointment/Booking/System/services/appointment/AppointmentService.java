package com.AppointmentBookingSystem.Appointment.Booking.System.services.appointment;



import com.AppointmentBookingSystem.Appointment.Booking.System.entity.Appointment;
import com.AppointmentBookingSystem.Appointment.Booking.System.entity.TimeSlot;
import com.AppointmentBookingSystem.Appointment.Booking.System.entity.User;
import com.AppointmentBookingSystem.Appointment.Booking.System.repository.AppointmentRepository;
import com.AppointmentBookingSystem.Appointment.Booking.System.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AppointmentBookingSystem.Appointment.Booking.System.entity.TimeSlot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public List<Appointment> getUserAppointments(User user) {
        return appointmentRepository.findByUser(user);
    }

    public Appointment bookAppointment(User user, Long timeSlotId) throws Exception {
        Optional<TimeSlot> timeSlotOpt = timeSlotRepository.findById(timeSlotId);
        if (timeSlotOpt.isEmpty() || timeSlotOpt.get().isBooked()) {
            throw new Exception("Time slot is already booked or not available.");
        }

        TimeSlot timeSlot = timeSlotOpt.get();
        timeSlot.setBooked(true);
        timeSlotRepository.save(timeSlot);

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setAppointmentTime(timeSlot.getStartTime());
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long appointmentId,Long timeSlotId) throws Exception {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
        if (appointmentOpt.isEmpty()) {
            throw new Exception("Appointment not found.");
        }

        Appointment appointment = appointmentOpt.get();
        appointment.setCancelled(true);
        Optional<TimeSlot> timeSlotOpt = timeSlotRepository.findById(timeSlotId);
        TimeSlot timeSlot = timeSlotOpt.get();
        timeSlot.setBooked(false);
        timeSlotRepository.save(timeSlot);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long appointmentId) throws Exception {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new Exception("Appointment not found with ID: " + appointmentId));
    }

    public List<Appointment> filterAppointments(Long userId, LocalDateTime startDate, LocalDateTime endDate, Boolean isCancelled) {
        return appointmentRepository.findFilteredAppointments(userId, startDate, endDate, isCancelled);
    }

}
