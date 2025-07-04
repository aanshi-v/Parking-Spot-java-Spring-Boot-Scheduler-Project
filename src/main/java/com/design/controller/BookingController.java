package com.design.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.ApiResponse;
import com.design.dto.BookingRequestDTO;
import com.design.dto.BookingResponseDTO;
import com.design.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/spot")
@RequiredArgsConstructor
public class BookingController {
	
	private final BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<ApiResponse<BookingResponseDTO>> raiseRequest(@RequestBody BookingRequestDTO dto) {
        BookingResponseDTO response = bookingService.raiseBooking(dto);
        return ResponseEntity.ok(new ApiResponse<>("Booking request raised successfully", 200, response));
    }

}
