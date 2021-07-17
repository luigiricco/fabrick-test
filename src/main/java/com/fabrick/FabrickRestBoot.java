package com.fabrick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.fabrick")
@ComponentScan(basePackages = "com.fabrick")
public class FabrickRestBoot {
    public static void main(String[] args) {
        SpringApplication.run(FabrickRestBoot.class, args);
    }
}
