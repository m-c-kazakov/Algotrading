package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.service.broker.consumer.DataConsumer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SpecificationOfStrategyServiceImplTest extends IntegrationTestBased {

    @MockBean
    DataConsumer dataConsumer;
    @MockBean
    SchedulingService schedulingService;

    @Autowired
    SpecificationOfStrategyService specificationOfStrategyService;
    @Autowired
    RandomPopulationCreationManager randomPopulationCreationManager;

    @SpyBean
    SpecificationOfStrategyRepository repository;


    @Value("${app.kafka.consumer.max_poll_records_config}")
    Integer max_poll_records_config;

    @Test
    void findByHashCode() {

        doReturn(max_poll_records_config * 5).when(dataConsumer).poll();

        SpecificationOfStrategy specificationOfStrategy = randomPopulationCreationManager.execute();


        SpecificationOfStrategy entity = specificationOfStrategyService.save(specificationOfStrategy);
        assertThat(specificationOfStrategyService.findByHashCode(entity.getHashCode())).isPresent();
        assertThat(specificationOfStrategyService.findByHashCode(entity.getHashCode())).isPresent();
        verify(repository, times(1)).findByHashCode(anyString());
    }
}