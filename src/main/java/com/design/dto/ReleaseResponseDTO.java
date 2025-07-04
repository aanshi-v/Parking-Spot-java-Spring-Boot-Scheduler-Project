package com.design.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReleaseResponseDTO {
	
	private String spotNumber;
    private String status;
    private LocalDate fromDate;
    private LocalDate toDate;

}
