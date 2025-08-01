package com.design.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpotDTO {

	 private String spotNumber;
	    private Boolean isAvailable;
	    private Long employeeId;
}
