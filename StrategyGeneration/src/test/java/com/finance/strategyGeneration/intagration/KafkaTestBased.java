package com.finance.strategyGeneration.intagration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;


public abstract class KafkaTestBased extends IntegrationTestBased {

    private static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.0"));

    @BeforeAll
    public static void start() {
        kafka.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("app.kafka.bootstrap_servers_config", kafka::getBootstrapServers);
    }
}
