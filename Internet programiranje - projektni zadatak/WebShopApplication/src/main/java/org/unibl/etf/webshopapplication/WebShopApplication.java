package org.unibl.etf.webshopapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"org.unibl.etf.webshopapplication.*", "org.unibl.etf.webshopapplication"})
@EntityScan(basePackages = "org.unibl.etf.webshopapplication.model.entities")
@EnableJpaRepositories

public class WebShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:4200");
                registry.addMapping("/*/*").allowedOrigins("http://localhost:4200");
                registry.addMapping("/*/*/*").allowedOrigins("http://localhost:4200");
            }
        };
    }
}