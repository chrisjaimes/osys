package com.sitwo.osys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import config.Constants;
import config.PropertyReader;


@SpringBootApplication(scanBasePackages={"com.sitwo.osys","config", "admin", "error"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}