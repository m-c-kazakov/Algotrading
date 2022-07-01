package com.finance.createIndicatorData.intagration;

import com.finance.createIndicatorData.intagration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;


@IT
public abstract class IntegrationTestBased {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.4")
            .withClasspathResourceMapping("00_createTables.sql", "/docker-entrypoint-initdb.d/00_createTables.sql",
                    BindMode.READ_ONLY)
            .withClasspathResourceMapping("01_insertData.sql", "/docker-entrypoint-initdb.d/01_insertData.sql",
                    BindMode.READ_ONLY);

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

}
