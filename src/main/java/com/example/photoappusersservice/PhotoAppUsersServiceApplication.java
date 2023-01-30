package com.example.photoappusersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity(debug = true)
public class PhotoAppUsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppUsersServiceApplication.class, args);
    }

}
