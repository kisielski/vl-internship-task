package com.virtuslab.internship.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.virtuslab.internship"})
public class BasketApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasketApplication.class, args);
    }
}
