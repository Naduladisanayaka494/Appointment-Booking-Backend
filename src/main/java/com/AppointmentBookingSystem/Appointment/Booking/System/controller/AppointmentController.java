package com.AppointmentBookingSystem.Appointment.Booking.System.controller;


import com.AppointmentBookingSystem.Appointment.Booking.System.entity.Appointment;
import com.AppointmentBookingSystem.Appointment.Booking.System.entity.User;
import com.AppointmentBookingSystem.Appointment.Booking.System.services.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestParam Long userId, @RequestParam Long timeSlotId) throws Exception {
        User user = new User();
        user.setId(userId);
        return appointmentService.bookAppointment(user, timeSlotId);
    }

    @DeleteMapping("/{appointmentId}")
    public void cancelAppointment(@PathVariable Long appointmentId) throws Exception {
        appointmentService.cancelAppointment(appointmentId);
    }
}
