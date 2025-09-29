package com.example.auroraai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuroraAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAiApplication.class, args);
    }

}
