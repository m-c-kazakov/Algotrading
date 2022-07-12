package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.intagration.KafkaTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.DataSender;
import com.finance.strategyGeneration.service.broker.KafkaSender;
import com.finance.strategyGeneration.service.broker.StringValue;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

class SchedulingServiceImplTest extends KafkaTestBased {

    @Qualifier("submittedStrategies")
    @Autowired
    List<StringValue> submittedStrategies;
    @MockBean
    GeneticAlgorithm geneticAlgorithm;
    @Autowired
    DataSender kafkaSender;
    @Autowired
    SchedulingService schedulingService;
    RandomPopulationCreationManager randomStrategy;

    @TestConfiguration
    static class SchedulingServiceImplTestConfiguration {

        @Bean("submittedStrategies")
        public List<StringValue> stringValues() {
            return new CopyOnWriteArrayList<>();
        }

        @Bean
        public DataSender dataSender(KafkaProducer<Long, StringValue> producer,
                                     @Value("app.kafka.topic_name") String topicName,
                                     List<StringValue> stringValues) {
            return new KafkaSender(producer, topicName, stringValues::add);
        }

    }

    @Test
    void execute() {
        List<SpecificationOfStrategy> specificationOfStrategies =
                Stream.iterate(0, integer -> integer < 5, integer -> integer + 1)
                        .map(integer -> randomStrategy.execute())
                        .toList();

        Mockito.doReturn(specificationOfStrategies).when(geneticAlgorithm).execute();

        await()
                .atMost(60, TimeUnit.SECONDS)
                .until(() -> submittedStrategies.size() == specificationOfStrategies.size());
        assertThat(submittedStrategies).hasSameElementsAs(
                specificationOfStrategies.stream()
                        .map(SpecificationOfStrategy::getId)
                        .map(id -> new StringValue(id, String.valueOf(id)))
                        .toList()
        );


    }


}