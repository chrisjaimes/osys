package com.sitwo.osys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.sitwo.osys","config", "admin", "hello"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}