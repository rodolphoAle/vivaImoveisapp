package com.vivaImoveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
=======
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.repository")
@EntityScan(basePackages = "com.models")
>>>>>>> 4557595 (fazendo a conecção com o banco e salvando os dados no banco)
public class VivaImoveisApplication {

	public static void main(String[] args) {
		SpringApplication.run(VivaImoveisApplication.class, args);
	}

}
