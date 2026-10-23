package com.pedrcpereira.nkbeauty.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                                    .title("Nicole Kenttä Beauty API")
                                    .version("0.1.0")
                                    .description("Booking API for Nicole Kenttä Beauty: services, schedule, clients and bookings."));
    }
}
