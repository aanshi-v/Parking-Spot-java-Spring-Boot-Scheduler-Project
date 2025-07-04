package com.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Parking2Application {

	public static void main(String[] args) {
		SpringApplication.run(Parking2Application.class, args);
	}

}
