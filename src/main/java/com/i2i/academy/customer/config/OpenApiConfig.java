package com.i2i.academy.customer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customerOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer Management API")
                        .description("A layered Spring Boot RESTful API for managing customers, "
                                + "backed by Oracle XE and documented with OpenAPI / Swagger UI.")
                        .version("1.0.0")
                        .contact(new Contact().name("i2i Academy"))
                        .license(new License().name("i2i Systems Turkey 2026")));
    }
}
