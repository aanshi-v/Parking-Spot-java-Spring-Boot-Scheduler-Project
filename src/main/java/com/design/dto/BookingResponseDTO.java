package com.design.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingResponseDTO {
	
	private String spotNumber;
    private Long employeeId;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

}
