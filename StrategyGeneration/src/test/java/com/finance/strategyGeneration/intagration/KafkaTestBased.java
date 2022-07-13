package com.finance.strategyGeneration.intagration;

import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

//@IT
public abstract class KafkaTestBased extends IntegrationTestBased {

    private static final Logger log = LoggerFactory.getLogger(KafkaTestBased.class);

    private static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.0"));

    private static String TOPIC_NAME;

    @BeforeAll
    public static void start() throws ExecutionException, InterruptedException, TimeoutException {
        kafka.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("app.kafka.bootstrap_servers_config", kafka::getBootstrapServers);
    }

    public void setTopicName(@Value("app.kafka.topic_name") String topicName) {
        KafkaTestBased.TOPIC_NAME = topicName;
    }
}
