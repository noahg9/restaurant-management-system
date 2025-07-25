package com.noahg9.restaurant.config;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Application config.
 */
@Configuration
@EnableCaching
@EnableAsync
public class ApplicationConfig {
    /**
     * Model mapper model mapper.
     *
     * @return the model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Cors configurer web mvc configurer.
     *
     * @return the web mvc configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:9243")
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name());
            }
        };
    }
}
