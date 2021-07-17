package com.fabrick.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaRepositories(basePackages = "com.fabrick")
@EnableAutoConfiguration
@Configuration
public class FabrickConfig implements WebMvcConfigurer {
}
