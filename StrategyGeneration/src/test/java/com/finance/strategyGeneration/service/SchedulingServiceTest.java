package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.config.configurationProperties.KafkaConsumerConfigurationProperties;
import com.finance.strategyGeneration.config.configurationProperties.KafkaProducerConfigurationProperties;
import com.finance.strategyGeneration.intagration.KafkaTestBased;
import com.finance.strategyGeneration.mapper.SpecificationOfStrategyMapper;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.consumer.DataConsumer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;


@EnableConfigurationProperties({KafkaProducerConfigurationProperties.class, KafkaConsumerConfigurationProperties.class})
public class SchedulingServiceTest extends KafkaTestBased {


    @MockBean
    GeneticAlgorithm geneticAlgorithm;
    @MockBean
    SpecificationOfStrategyService specificationOfStrategyService;


    @Autowired
    SchedulingService schedulingService;

    @Autowired
    SpecificationOfStrategyMapper mapper;

    @Autowired
    RandomPopulationCreationManager randomPopulationCreationManager;
    @Autowired
    DataConsumer dataConsumer;
    @Value("${app.populationCreation.frontierForCreatingNewStrategies}")
    Integer frontierForCreatingNewStrategies;

    @Test
    void execute() {

        List<SpecificationOfStrategy> specificationOfStrategies =
                Stream.iterate(0, integer -> integer < frontierForCreatingNewStrategies, integer -> integer + 1)
                        .map(integer -> randomPopulationCreationManager.execute())
                        .toList();

        Mockito.doReturn(specificationOfStrategies).when(geneticAlgorithm).execute();
        Mockito.doReturn(0).when(specificationOfStrategyService).findTheNumberOfUntestedStrategies();

        schedulingService.execute();

        await()
                .atMost(30, TimeUnit.SECONDS)
                .until(() -> dataConsumer.poll() == specificationOfStrategies.size());
//        assertThat(submittedStrategies).hasSameElementsAs(specificationOfStrategies.stream().map(mapper::mapTo).toList());
    }
}
