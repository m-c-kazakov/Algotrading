package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({
        "classpath:sql/data.sql"
})
@Transactional
class InformationOfCandlesRepositoryTest extends IntegrationTestBased {

    @Autowired
    InformationOfCandlesRepository repository;

    @Test
    void existsByHashCode() {
        assertThat(repository.existsByHashCode("3")).isTrue();
    }

    @Test
    void findByHashCode() {
        assertThat(repository.findByHashCode("3")).isPresent();
    }
}