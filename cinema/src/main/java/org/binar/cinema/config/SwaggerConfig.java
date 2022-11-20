package org.binar.cinema.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Reservasi Bioskop - Joko Prabowo")
                        .version("V1.0.0")
                        .description("Microservice untuk Cinema Service"));
    }
}

