package com.mart.greetingsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class GreetingsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GreetingsServiceApplication.class, args);
    }

}
