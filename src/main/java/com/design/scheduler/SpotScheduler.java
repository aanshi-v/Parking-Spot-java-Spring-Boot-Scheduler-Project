package com.design.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.design.service.SpotService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpotScheduler {

	private final SpotService spotService;

    
//    @Scheduled(cron = "0 0 9 * * *")	  // Run every day at 9 AM  
     @Scheduled(fixedRate = 60000)     // (Optional for local testing) Every 1 minute
//	@Scheduled(fixedRate = 30000)        // runs every 30 seconds
    public void runAutoAllocation() {
        System.out.println("📅 Scheduler: Running auto-allocation of booking requests...");
        spotService.processRequests();
    }
}
