package com.design.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
	
	 private String name;
	    private String email;
	    private String phoneNumber;
	    private String department;
	    private String designation;

}
