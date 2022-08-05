package com.finance.strategy.statistics.intagration

import com.finance.strategy.statistics.intagration.annotation.IT
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.containers.PostgreSQLContainer

@IT
@Sql("/test-schema.sql")
abstract class IntegrationTestBased {

    companion object {

        private val container = PostgreSQLContainer("postgres:14.4")
        private val log: Logger = LoggerFactory.getLogger(IntegrationTestBased::class.java)

        @BeforeAll
        @JvmStatic
        internal fun runContainer() {
            log.info("Запуск тестового контейнере")
            container.start()
        }

        @DynamicPropertySource
        @JvmStatic
        internal fun postgresProperties(registry: DynamicPropertyRegistry) {
            log.info("Установка свойств для тестового контейнера spring.datasource.url={}", container.jdbcUrl)
            registry.add("spring.datasource.url") { container.jdbcUrl }
        }

        @AfterAll
        @JvmStatic
        internal fun stopContainer() {
            log.info("Остановка тестового контейнере")
            container.stop()
        }
    }
}