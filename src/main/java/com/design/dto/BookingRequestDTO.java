package com.design.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingRequestDTO {
	
	private Long employeeId;
    private String spotNumber;
    private LocalDate startDate;
    private LocalDate endDate;

}
