package com.AppointmentBookingSystem.Appointment.Booking.System.services.auth;


import com.AppointmentBookingSystem.Appointment.Booking.System.dto.SignUpRequest;
import com.AppointmentBookingSystem.Appointment.Booking.System.dto.UserDto;

public interface AuthService {
    UserDto createdDataEntry(SignUpRequest signuprequest);
    UserDto createdAdmin(SignUpRequest signuprequest);
    boolean hasAdminwithemail(String email);
}
