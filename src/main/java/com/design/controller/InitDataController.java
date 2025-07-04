package com.design.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.EmployeeDTO;
import com.design.dto.ParkingSpotDTO;
import com.design.entity.Employee;
import com.design.entity.ParkingSpot;
import com.design.repository.EmployeeRepository;
import com.design.repository.ParkingSpotRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InitDataController {
	
	private final EmployeeRepository employeeRepo;
    private final ParkingSpotRepository spotRepo;

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDTO dto) {
        Employee emp = Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .department(dto.getDepartment())
                .designation(dto.getDesignation())
                .build();
        return ResponseEntity.ok(employeeRepo.save(emp));
    }

    @PostMapping("/spots")
    public ResponseEntity<ParkingSpot> saveSpot(@RequestBody ParkingSpotDTO dto) {
        Employee emp = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        ParkingSpot spot = ParkingSpot.builder()
                .spotNumber(dto.getSpotNumber())
                .isAvailable(dto.getIsAvailable())
                .employee(emp)
                .releaseStartDate(emp.getId() != null ? java.time.LocalDate.now() : null)
                .releaseEndDate(emp.getId() != null ? java.time.LocalDate.now().plusDays(1) : null)
                .releaseReason("Initial allocation")
                .build();
        return ResponseEntity.ok(spotRepo.save(spot));
    }

}
