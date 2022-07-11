package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.StatisticsOfStrategyRepository;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithmImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
class GeneticAlgorithmImplTest extends IntegrationTestBased {

    @Autowired
    GeneticAlgorithmImpl geneticAlgorithm;
    @Autowired
    StatisticsOfStrategyRepository repository;

    @Test
    void execute() {
        List<SpecificationOfStrategy> execute = geneticAlgorithm.execute();
        assertThat(execute)
                .isNotEmpty();
    }
}