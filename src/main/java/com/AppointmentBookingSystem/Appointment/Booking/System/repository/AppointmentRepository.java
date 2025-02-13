package com.AppointmentBookingSystem.Appointment.Booking.System.repository;



import com.AppointmentBookingSystem.Appointment.Booking.System.entity.Appointment;
import com.AppointmentBookingSystem.Appointment.Booking.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUser(User user);

    @Query("SELECT a FROM Appointment a WHERE " +
            "(:userId IS NULL OR a.user.id = :userId) AND " +
            "(:startDate IS NULL OR a.appointmentTime >= :startDate) AND " +
            "(:endDate IS NULL OR a.appointmentTime <= :endDate) AND " +
            "(:isCancelled IS NULL OR a.isCancelled = :isCancelled)")
    List<Appointment> findFilteredAppointments(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("isCancelled") Boolean isCancelled
    );

}
