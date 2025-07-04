package com.design.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.design.dto.ReleaseResponseDTO;
import com.design.dto.ReleaseSpotDTO;
import com.design.entity.ParkingSpot;
import com.design.entity.SpotBooking;
import com.design.exception.BadRequestException;
import com.design.exception.ResourceNotFoundException;
import com.design.repository.EmployeeRepository;
import com.design.repository.ParkingSpotRepository;
import com.design.repository.SpotBookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpotService {

	private final ParkingSpotRepository spotRepo;
    private final EmployeeRepository empRepo;
    private final SpotBookingRepository bookingRepo;

    @Transactional
    public ReleaseResponseDTO releaseSpot(ReleaseSpotDTO dto) {
        ParkingSpot spot = spotRepo.findBySpotNumber(dto.getSpotNumber())
            .orElseThrow(() -> new ResourceNotFoundException("Spot not found"));

        if (!spot.getEmployee().getId().equals(dto.getEmployeeId())) {
            throw new BadRequestException("You are not the owner of this spot");
        }

        spot.setIsAvailable(true);
        spot.setReleaseStartDate(dto.getStartDate());
        spot.setReleaseEndDate(dto.getEndDate());
        spot.setReleaseReason(dto.getReason());
        spotRepo.save(spot);

        bookingRepo.save(SpotBooking.builder()
            .employee(spot.getEmployee())
            .spotNumber(dto.getSpotNumber())
            .startDate(dto.getStartDate())
            .endDate(dto.getEndDate())
            .status(SpotBooking.Status.APPROVED)
            .actionType("RELEASE")
            .reason(dto.getReason())
            .build());

        return new ReleaseResponseDTO(dto.getSpotNumber(), "RELEASED", dto.getStartDate(), dto.getEndDate());
    }

    public Map<String, Integer> processRequests() {
        List<SpotBooking> pending = bookingRepo.findByStatus(SpotBooking.Status.PENDING);
        int approved = 0, rejected = 0;

        for (SpotBooking req : pending) {
            ParkingSpot spot = spotRepo.findBySpotNumber(req.getSpotNumber()).orElse(null);
            if (spot != null && spot.getIsAvailable()
                && !req.getStartDate().isBefore(spot.getReleaseStartDate())
                && !req.getEndDate().isAfter(spot.getReleaseEndDate())) {
                req.setStatus(SpotBooking.Status.APPROVED);
                spot.setIsAvailable(false);
                approved++;
            } else {
                req.setStatus(SpotBooking.Status.REJECTED);
                rejected++;
            }
        }

        bookingRepo.saveAll(pending);
        return Map.of("approved", approved, "rejected", rejected);
    }
}
