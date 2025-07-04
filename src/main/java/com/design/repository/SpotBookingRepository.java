package com.design.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.design.entity.SpotBooking;

@Repository
public interface SpotBookingRepository extends JpaRepository<SpotBooking, Long> {

	List<SpotBooking> findByStatus(SpotBooking.Status status);
    boolean existsBySpotNumberAndStartDate(String spotNumber, LocalDate startDate);
}
