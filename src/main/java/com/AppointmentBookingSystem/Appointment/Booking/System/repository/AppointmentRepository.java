package com.AppointmentBookingSystem.Appointment.Booking.System.repository;



import com.AppointmentBookingSystem.Appointment.Booking.System.entity.Appointment;
import com.AppointmentBookingSystem.Appointment.Booking.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUser(User user);
}
