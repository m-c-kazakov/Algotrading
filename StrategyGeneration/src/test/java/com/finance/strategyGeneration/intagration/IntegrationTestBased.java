package com.finance.strategyGeneration.intagration;

import com.finance.strategyGeneration.intagration.annotation.IT;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;


@IT
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class IntegrationTestBased {

    static MongoDBContainer container = new MongoDBContainer("mongo:5.0.9");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }

}
