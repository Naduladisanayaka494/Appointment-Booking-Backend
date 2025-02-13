package com.AppointmentBookingSystem.Appointment.Booking.System.controller;


import com.AppointmentBookingSystem.Appointment.Booking.System.entity.Appointment;
import com.AppointmentBookingSystem.Appointment.Booking.System.entity.User;
import com.AppointmentBookingSystem.Appointment.Booking.System.services.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getUserAppointments(@RequestParam Long userId) {
        User user = new User();
        user.setId(userId);
        return appointmentService.getUserAppointments(user);
    }
    @GetMapping("/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable Long appointmentId) throws Exception {
        return appointmentService.getAppointmentById(appointmentId);
    }
    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestParam Long userId, @RequestParam Long timeSlotId) throws Exception {
        User user = new User();
        user.setId(userId);
        return appointmentService.bookAppointment(user, timeSlotId);
    }

    @DeleteMapping
    public void cancelAppointment(@RequestParam Long appointmentId,@RequestParam Long timeSlotId) throws Exception {
        appointmentService.cancelAppointment(appointmentId,timeSlotId);
    }

    @GetMapping("/filter")
    public List<Appointment> filterAppointments(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Boolean isCancelled) {

        return appointmentService.filterAppointments(userId, startDate, endDate, isCancelled);
    }
}
