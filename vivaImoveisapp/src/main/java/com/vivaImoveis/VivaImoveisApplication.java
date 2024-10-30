package com.vivaImoveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.repository")
@EntityScan(basePackages = "com.models")
public class VivaImoveisApplication {

	public static void main(String[] args) {
		SpringApplication.run(VivaImoveisApplication.class, args);
	}

}