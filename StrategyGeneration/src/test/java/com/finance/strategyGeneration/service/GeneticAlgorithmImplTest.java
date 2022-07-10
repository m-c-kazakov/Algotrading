package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithmImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
class GeneticAlgorithmImplTest extends IntegrationTestBased {

    @Autowired
    GeneticAlgorithmImpl geneticAlgorithm;

    @Test
    void execute() {
        assertThat(geneticAlgorithm.execute()).isNotEmpty();
    }
}