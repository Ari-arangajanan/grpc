package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapitalServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CapitalServiceApp.class, args);
        System.out.println("Hello! This is client");
    }
}