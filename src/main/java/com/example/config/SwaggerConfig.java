package com.example.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("HotelAPI")
                        .description("Ejemplo de API REST con hoteles")
                        .contact(new Contact()
                                .name("Isaac")
                                .email("isaac@isaac.com")
                                .url("https://isaac.com"))
                        .version("1.0"));
    }

}
