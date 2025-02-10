package com.AppointmentBookingSystem.Appointment.Booking.System.services.timeslot;



import com.AppointmentBookingSystem.Appointment.Booking.System.entity.TimeSlot;
import com.AppointmentBookingSystem.Appointment.Booking.System.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public List<TimeSlot> getFilteredSlots(LocalDateTime startDate, LocalDateTime endDate, Boolean isBooked) {
        return timeSlotRepository.findFilteredSlots(startDate, endDate, isBooked);
    }

    public TimeSlot createTimeSlot(LocalDateTime startTime) {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(startTime);
        return timeSlotRepository.save(timeSlot);
    }
}

