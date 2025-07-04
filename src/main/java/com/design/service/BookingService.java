package com.design.service;

import java.time.temporal.ChronoUnit;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.design.dto.BookingRequestDTO;
import com.design.dto.BookingResponseDTO;
import com.design.entity.Employee;
import com.design.entity.SpotBooking;
import com.design.exception.BadRequestException;
import com.design.exception.ResourceNotFoundException;
import com.design.repository.EmployeeRepository;
import com.design.repository.SpotBookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	
	private final EmployeeRepository empRepo;
    private final SpotBookingRepository bookingRepo;

    public BookingResponseDTO raiseBooking(BookingRequestDTO dto) {
        Employee emp = empRepo.findById(dto.getEmployeeId())
            .orElseThrow(() -> new ResourceNotFoundException("Invalid employee ID"));
        
        // Validation: booking must be for 1 day only
        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        if (days != 1) {
            throw new BadRequestException("Booking must be for exactly 1 day");
        }

        if (bookingRepo.existsBySpotNumberAndStartDate(dto.getSpotNumber(), dto.getStartDate())) {
            throw new BadRequestException("Spot already booked on this date");
        }

        SpotBooking booking = SpotBooking.builder()
            .employee(emp)
            .spotNumber(dto.getSpotNumber())
            .startDate(dto.getStartDate())
            .endDate(dto.getEndDate())
            .status(SpotBooking.Status.PENDING)
            .actionType("REQUEST")
            .build();
        bookingRepo.save(booking);

        return BookingResponseDTO.builder()
            .employeeId(emp.getId())
            .spotNumber(dto.getSpotNumber())
            .status("PENDING")
            .startDate(dto.getStartDate())
            .endDate(dto.getEndDate())
            .build();
    }

}
