package me.dio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library Management System API")
                        .description("""
                                This is an API made in Java using Spring Web concepts with the function
                                of serving as a library management system.

                                Through this system, it is possible to control the entries and exits of
                                books and users, in addition to managing and improving the loan system,
                                ensuring more effective, dynamic and simplified management.""")
                        .contact(new Contact().name("Marco Antonio").email("Marcofleitepro@gmail.com").url("https://github.com/Marcoantt"))
                        .version("1.0"));
    }
}
