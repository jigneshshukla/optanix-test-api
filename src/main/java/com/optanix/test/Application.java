package com.optanix.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jignesh
 * Spring Boot Application Main Class
 */
@SpringBootApplication(scanBasePackages = {"com.optanix.test"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
