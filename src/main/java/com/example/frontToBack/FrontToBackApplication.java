package com.example.frontToBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories("com.example.frontToBack.repository")
@EntityScan("com.example.frontToBack.model")
public class FrontToBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontToBackApplication.class, args);
	}

}
