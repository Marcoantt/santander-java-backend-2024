package me.dio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(servers = { @Server(url = "http://sjb-2024-prd.up.railway.app/", description = "Default Server URL")})
@SpringBootApplication
public class SantanderJavaBackend2024Application {

	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(SantanderJavaBackend2024Application.class, args);
	}

}
