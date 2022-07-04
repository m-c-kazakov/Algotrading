package com.finance.check.strategy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CheckStrategyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckStrategyApplication.class, args);
    }

}
