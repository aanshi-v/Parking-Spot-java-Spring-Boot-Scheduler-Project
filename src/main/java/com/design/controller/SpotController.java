package com.design.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.ApiResponse;
import com.design.dto.ReleaseResponseDTO;
import com.design.dto.ReleaseSpotDTO;
import com.design.service.SpotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotController {
	
	private final SpotService spotService;

    @PostMapping("/release")
    public ResponseEntity<ApiResponse<ReleaseResponseDTO>> releaseSpot(@RequestBody ReleaseSpotDTO dto) {
        ReleaseResponseDTO response = spotService.releaseSpot(dto);
        return ResponseEntity.ok(new ApiResponse<>("Spot released successfully", 200, response));
    }

    @PutMapping("/bookings/auto-allocate")
    public ResponseEntity<ApiResponse<Object>> autoAllocate() {
        var result = spotService.processRequests();
        return ResponseEntity.ok(new ApiResponse<>("Booking requests processed", 200, result));
    }

}
