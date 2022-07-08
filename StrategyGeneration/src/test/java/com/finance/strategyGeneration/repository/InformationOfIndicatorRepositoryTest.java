package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class InformationOfIndicatorRepositoryTest extends IntegrationTestBased {

    @Autowired
    InformationOfIndicatorRepository repository;


    @Test
    void existsByHashCode() {
        assertThat(repository.existsByHashCode(3)).isTrue();
    }

    @Test
    void findByHashCode() {
        assertThat(repository.findByHashCode(3)).isPresent();
    }
}