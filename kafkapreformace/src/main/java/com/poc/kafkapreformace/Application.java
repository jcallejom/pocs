package com.poc.kafkapreformace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(scanBasePackages = {"com.poc"} )
@OpenAPIDefinition(info = 
	@Info(title = "Prototype API", description = "A REST microservice with direct access to SQL databases using JPA")
)
//@EntityScan(basePackages = {"com.poc.base.core.audit.model","com.poc.example.infrastructure.out.db.jpa.entity"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
