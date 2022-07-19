package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.intagration.KafkaTestBased;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithmImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
class GeneticAlgorithmImplTest extends KafkaTestBased {

    @Autowired
    GeneticAlgorithmImpl geneticAlgorithm;
    @Autowired
    SpecificationOfStrategyRepository repository;

    @Test
    void execute() {
        geneticAlgorithm.execute();
        Optional<Integer> optionalInteger = repository.countAll();
        assertThat(optionalInteger).isPresent();
        optionalInteger.ifPresent(value -> {
            System.out.println("Количество найденных стратегий=" + value);
            assertThat(value).isGreaterThan(0);
        });
    }
}