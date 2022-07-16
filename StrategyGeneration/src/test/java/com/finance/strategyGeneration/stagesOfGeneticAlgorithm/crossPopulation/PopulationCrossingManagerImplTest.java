package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.consumer.DataConsumer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.PopulationCreationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

class PopulationCrossingManagerImplTest extends IntegrationTestBased {

    @MockBean
    DataConsumer dataConsumer;

    @Autowired
    PopulationCrossingManagerImpl populationCrossingManager;
    @Autowired
    PopulationCreationManager populationCreationManager;

    @Value("${app.kafka.consumer.max_poll_records_config}")
    Integer max_poll_records_config;

    @Test
    void execute() {

        doReturn(max_poll_records_config*5).when(dataConsumer).poll();

        List<SpecificationOfStrategy> specificationOfStrategies = populationCreationManager.execute();

        assertThat(populationCrossingManager.execute(specificationOfStrategies))
                .isNotEmpty()
                .hasSizeLessThan(specificationOfStrategies.size() * 10);
    }
}