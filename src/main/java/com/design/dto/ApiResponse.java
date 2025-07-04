package com.design.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class ApiResponse<T> {
	
	 private String message;
	    private int statusCode;
	    private T data;

}
