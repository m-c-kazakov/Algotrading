package com.finance.strategyGeneration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.config.configurationProperties.KafkaConfigurationProperties;
import com.finance.strategyGeneration.intagration.KafkaTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.DataProducer;
import com.finance.strategyGeneration.service.broker.JsonSerializer;
import com.finance.strategyGeneration.service.broker.Producer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.finance.strategyGeneration.service.broker.JsonSerializer.OBJECT_MAPPER;
import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.RETRIES_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@EnableConfigurationProperties(value = KafkaConfigurationProperties.class)
class SchedulingServiceImplTest extends KafkaTestBased {

    @Qualifier("submittedStrategies")
    @Autowired
    List<SpecificationOfStrategy> submittedStrategies;
    @MockBean
    GeneticAlgorithm geneticAlgorithm;
    @MockBean
    SpecificationOfStrategyService specificationOfStrategyService;
    @Autowired
    DataProducer kafkaSender;
    @Autowired
    SchedulingService schedulingService;

    @Autowired
    RandomPopulationCreationManager randomPopulationCreationManager;

    @Test
    void execute() {

        List<SpecificationOfStrategy> specificationOfStrategies =
                Stream.iterate(0, integer -> integer < 5, integer -> integer + 1)
                        .map(integer -> randomPopulationCreationManager.execute())
                        .toList();

        Mockito.doReturn(specificationOfStrategies).when(geneticAlgorithm).execute();
        Mockito.doReturn(0).when(specificationOfStrategyService).findTheNumberOfUntestedStrategies();

        schedulingService.execute();

        await()
                .atMost(30, TimeUnit.SECONDS)
                .until(() -> submittedStrategies.size() == specificationOfStrategies.size());
        assertThat(submittedStrategies).hasSameElementsAs(specificationOfStrategies);
    }

    @TestConfiguration
    static class SchedulingServiceImplTestConfiguration {

        @Bean
        public SchedulingService schedulingService(GeneticAlgorithm geneticAlgorithm, DataProducer dataProducer, SpecificationOfStrategyService specificationOfStrategyService) {
            return new SchedulingServiceImpl(geneticAlgorithm, dataProducer, specificationOfStrategyService);
        }

        @Bean("submittedStrategies")
        public List<SpecificationOfStrategy> SpecificationOfStrategys() {
            return new CopyOnWriteArrayList<>();
        }

        @Bean
        public DataProducer dataSender(KafkaProducer<Long, SpecificationOfStrategy> producer,
                                       @Value("app.kafka.topic_name") String topicName,
                                       List<SpecificationOfStrategy> SpecificationOfStrategys) {
            return new Producer(producer, topicName, SpecificationOfStrategys::add);
        }

        @Bean
        public KafkaProducer<Long, SpecificationOfStrategy> kafkaProducer(KafkaConfigurationProperties properties) {
            Properties props = new Properties();
            props.put(CLIENT_ID_CONFIG, properties.getClient_id_config());
            props.put(BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrap_servers_config());
            props.put(ACKS_CONFIG, properties.getAcks_config());
            props.put(RETRIES_CONFIG, properties.getRetries_config());
            props.put(BATCH_SIZE_CONFIG, properties.getBatch_size_config());
            props.put(LINGER_MS_CONFIG, properties.getLinger_ms_config());
            props.put(BUFFER_MEMORY_CONFIG, properties.getBuffer_memory_config()); //bytes
            props.put(MAX_BLOCK_MS_CONFIG, properties.getMax_block_ms_config()); //ms
            props.put(KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
            props.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            props.put(OBJECT_MAPPER, new ObjectMapper());

            KafkaProducer<Long, SpecificationOfStrategy> kafkaProducer = new KafkaProducer<>(props);

            var shutdownHook = new Thread(kafkaProducer::close);
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            return kafkaProducer;
        }

    }
}