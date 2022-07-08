package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StatisticsOfStrategyRepositoryTest extends IntegrationTestBased {

    @Autowired
    StatisticsOfStrategyRepository repository;




    @Test
    void findTheBestStatistics() {
        repository.findAll();
        repository.findTheBestStatistics(1);
    }
}