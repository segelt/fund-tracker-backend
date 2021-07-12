package com.fundtracker.fundtrackerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.fundtracker.fundtrackerbackend.resources"})
@ComponentScan({"com.fundtracker.fundtrackerbackend.services"})
@EntityScan("com.fundtracker.fundtrackerbackend.domain")
@EnableJpaRepositories("com.fundtracker.fundtrackerbackend.repositories")
public class FundTrackerBackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FundTrackerBackendApplication.class, args);
	}

}
