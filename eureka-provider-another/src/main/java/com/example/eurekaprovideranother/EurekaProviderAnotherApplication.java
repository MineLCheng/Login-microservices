package com.example.eurekaprovideranother;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaProviderAnotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderAnotherApplication.class, args);
    }

}
