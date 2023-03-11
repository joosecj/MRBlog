package com.mr.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI customOpenApi(){
    return new OpenAPI()
            .info(
                    new Info()
                            .title("RESTFul Api with java 17 and Spring Boot 3.0.2")
                            .version("v0.0.1")
                            .description("Project MRBlog")
                            .termsOfService("https://github.com/joosecj/MRBlog")
                            .license(
                                    new License().name("Apache 2.0")
                                            .url("https://github.com/joosecj/MRBlog")
                            )
            );
  }
}
