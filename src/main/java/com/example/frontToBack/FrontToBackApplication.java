package com.example.frontToBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories("com.example.frontToBack.repository")
@EntityScan("com.example.frontToBack.model")
public class FrontToBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontToBackApplication.class, args);
	}

}
