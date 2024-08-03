package com.b1aboa.wedug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories("com.b1aboa.wedug.repository")
public class WedugApplication {

	public static void main(String[] args) {
		SpringApplication.run(WedugApplication.class, args);
	}

}
