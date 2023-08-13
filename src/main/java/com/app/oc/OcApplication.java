package com.app.oc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD


@SpringBootApplication
public class ocApplication {

	public static void main(String[] args) {
		SpringApplication.run(ocApplication.class, args);
=======
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcApplication.class, args);
>>>>>>> d7a2e9ece82f551a0a933ebea8f83c5d7f78e446
	}

}
