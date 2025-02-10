package com.AppointmentBookingSystem.Appointment.Booking.System.controller;



import com.AppointmentBookingSystem.Appointment.Booking.System.entity.TimeSlot;
import com.AppointmentBookingSystem.Appointment.Booking.System.services.timeslot.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/slots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping("/filter")
    public List<TimeSlot> getFilteredSlots(
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Boolean isBooked) {
        return timeSlotService.getFilteredSlots(startDate, endDate, isBooked);
    }

    @PostMapping("/create")
    public TimeSlot createTimeSlot(@RequestParam LocalDateTime startTime) {
        return timeSlotService.createTimeSlot(startTime);
    }
}
