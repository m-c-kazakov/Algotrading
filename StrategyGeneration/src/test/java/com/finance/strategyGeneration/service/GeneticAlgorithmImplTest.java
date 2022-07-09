package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithmImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
class GeneticAlgorithmImplTest extends IntegrationTestBased {

    @Autowired
    GeneticAlgorithmImpl geneticAlgorithm;

    @Test
    void execute() {

        List<DescriptionOfStrategy> execute = geneticAlgorithm.execute();
        System.out.println("!!!!!!!!!!!!!!!");
    }
}