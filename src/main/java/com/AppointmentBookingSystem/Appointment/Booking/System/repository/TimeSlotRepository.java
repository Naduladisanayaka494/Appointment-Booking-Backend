package com.AppointmentBookingSystem.Appointment.Booking.System.repository;



import com.AppointmentBookingSystem.Appointment.Booking.System.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByIsBookedFalse();

    @Query("SELECT ts FROM TimeSlot ts WHERE " +
            "(:startDate IS NULL OR ts.startTime >= :startDate) AND " +
            "(:endDate IS NULL OR ts.startTime <= :endDate) AND " +
            "(:isBooked IS NULL OR ts.isBooked = :isBooked)")
    List<TimeSlot> findFilteredSlots(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("isBooked") Boolean isBooked
    );

}
