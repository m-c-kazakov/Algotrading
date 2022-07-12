package com.finance.strategyGeneration.intagration;

import com.finance.strategyGeneration.intagration.annotation.IT;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;

@IT
public abstract class KafkaTestBased {

    private static final Logger log = LoggerFactory.getLogger(KafkaTestBased.class);

    private static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.0"));

    private static String TOPIC_NAME;

    @BeforeAll
    public static void start() throws ExecutionException, InterruptedException, TimeoutException {
        kafka.start();

        log.info("topics creation...");
        try (var admin = AdminClient.create(Map.of(BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers()))) {
            var result = admin.createTopics(List.of(new NewTopic(TOPIC_NAME, 1, (short) 1)));

            for (var topicResult : result.values().values()) {
                topicResult.get(10, TimeUnit.SECONDS);
            }
        }
        log.info("topics created");
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("app.kafka.bootstrap_servers_config", kafka::getBootstrapServers);
    }

    public void setTopicName(@Value("app.kafka.topic_name") String topicName) {
        KafkaTestBased.TOPIC_NAME = topicName;
    }
}
