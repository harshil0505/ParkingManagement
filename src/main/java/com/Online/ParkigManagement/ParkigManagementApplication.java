package com.Online.ParkigManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.Online.ParkigManagement.Repository")
@EntityScan(basePackages = "com.Online.ParkigManagement.model")
public class ParkigManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkigManagementApplication.class, args);
	}

}
