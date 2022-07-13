package com.finance.check.strategy;

import com.finance.check.strategy.config.configurationProperties.KafkaConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(KafkaConfigurationProperties.class)
public class CheckStrategyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckStrategyApplication.class, args);
    }

}
