package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcServerApp {
    public static void main(String[] args) {
        SpringApplication.run(GrpcServerApp.class, args);
        System.out.println("Hello Server!");
    }
}