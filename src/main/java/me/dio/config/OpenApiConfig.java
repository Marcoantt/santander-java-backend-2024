package me.dio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/v3/api-docs", config);
        return new CorsFilter(source);
    }
}
