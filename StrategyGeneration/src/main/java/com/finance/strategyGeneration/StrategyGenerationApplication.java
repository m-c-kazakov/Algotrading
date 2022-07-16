package com.finance.strategyGeneration;

import com.finance.strategyGeneration.config.configurationProperties.KafkaConsumerConfigurationProperties;
import com.finance.strategyGeneration.config.configurationProperties.KafkaProducerConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({KafkaProducerConfigurationProperties.class, KafkaConsumerConfigurationProperties.class})
@SpringBootApplication
public class StrategyGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrategyGenerationApplication.class, args);
    }
}
