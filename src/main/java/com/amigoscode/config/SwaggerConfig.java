package com.amigoscode.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI softwareEngineerOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Software Engineer API")
                        .version("v1.0.0")
                        .description("Spring Boot + JPA 기반 소프트웨어 엔지니어 CRUD API 문서입니다.")
                        .contact(new Contact()
                                .name("chani")
                                .email("youngchan0510@gmail.com")
                                .url("https://github.com/zerochani/spring-software-engineer")
                        )
                );
    }
}
