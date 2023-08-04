package com.pivo.hotelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HoteloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoteloApplication.class, args);
	}

}
