package me.dio.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server URL")})
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
                                ensuring more effective, dynamic and simplified management.
                                
                                
                                WARNING - WARNING - WARNING
                                
                                Swagger UI is experiencing some stability issues due to CORS incompatibility, making it unfeasible to use this application through the Swagger interface.
                                
                                While this problem is not resolved, try using this application through Postman. All Requests necessary to use the application can be retrieved through the Swagger interface itself, but if you prefer, a Postman Collection will be made available with all available Requests.
                                
                                Link: https://www.postman.com/aerospace-administrator-89005134/workspace/santander-library-management-system-api/collection/37283600-953cbb31-3989-45e5-ba17-5fd240ca7572?action=share&creator=37283600
                                
                                I'm sorry about this, and I'm working on a solution as quickly as possible.
                                
                                
                                WARNING - WARNING - WARNING
                                
                                
                                
                                """)
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
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
