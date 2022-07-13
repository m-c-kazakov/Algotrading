package com.finance.check.strategy.intagration;

import com.finance.check.strategy.intagration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@IT
public abstract class KafkaTestBased {

    private static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.0"));

    @BeforeAll
    public static void start()  {
        kafka.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("app.kafka.bootstrap_servers_config", kafka::getBootstrapServers);
    }
}
