package com.finance.utils.intagration;

import com.finance.utils.intagration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;


@IT
@Sql({
        "classpath:sql/data.sql"
})
public abstract class IntegrationTestBased {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.4")
            .withCopyFileToContainer(
                    MountableFile.forHostPath("/home/maxim/Yandex.Disk/algotrading/data/candles-data"),
                    "/var/lib/candles-data");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

}
