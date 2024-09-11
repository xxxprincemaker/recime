package com.example.recime.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(
            @Value("${project.name}") final String appName,
            @Value("${project.version}") final String appVersion,
            @Value("${project.description}") final String appDescription,
            @Value("${project.maintainer.name}") final String maintainerName,
            @Value("${project.maintainer.email}") final String maintainerEmail
    ) {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title(appName)
                        .version(appVersion)
                        .description(appDescription)
                        .contact(new Contact()
                                .name(maintainerName)
                                .email(maintainerEmail)
                        )
                );
    }

}
