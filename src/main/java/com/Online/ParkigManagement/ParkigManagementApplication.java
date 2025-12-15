package com.Online.ParkigManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.Online.ParkigManagement.Model")
@EnableJpaRepositories("com.Online.ParkigManagement.Repository")
public class ParkigManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkigManagementApplication.class, args);
	}

}
