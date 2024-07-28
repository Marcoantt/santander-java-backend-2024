package me.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SantanderJavaBackend2024Application {

	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(SantanderJavaBackend2024Application.class, args);
	}

}
