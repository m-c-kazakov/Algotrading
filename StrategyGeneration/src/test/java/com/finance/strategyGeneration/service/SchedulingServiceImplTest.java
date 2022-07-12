package com.finance.strategyGeneration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.config.configurationProperties.KafkaConfigurationProperties;
import com.finance.strategyGeneration.intagration.KafkaTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.DataSender;
import com.finance.strategyGeneration.service.broker.JsonSerializer;
import com.finance.strategyGeneration.service.broker.KafkaSender;
import com.finance.strategyGeneration.service.broker.StringValue;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    List<StringValue> submittedStrategies;
    @MockBean
    GeneticAlgorithm geneticAlgorithm;
    @Autowired
    DataSender kafkaSender;
    @Autowired
    SchedulingService schedulingService;


    @Configuration
    static class SchedulingServiceImplTestConfiguration {

        @Bean
        public SchedulingService schedulingService(GeneticAlgorithm geneticAlgorithm, DataSender dataSender) {
            return new SchedulingServiceImpl(geneticAlgorithm, dataSender);
        }

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

        @Bean
        public KafkaProducer<Long, StringValue> kafkaProducer(KafkaConfigurationProperties properties) {
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

            KafkaProducer<Long, StringValue> kafkaProducer = new KafkaProducer<>(props);

            var shutdownHook = new Thread(kafkaProducer::close);
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            return kafkaProducer;
        }

    }

    @Test
    void execute() {
        List<SpecificationOfStrategy> specificationOfStrategies =
                Stream.iterate(0, integer -> integer < 5, integer -> integer + 1)
                        .map(integer -> SpecificationOfStrategy.builder().id(Long.valueOf(integer)).build())
                        .toList();

        Mockito.doReturn(specificationOfStrategies).when(geneticAlgorithm).execute();

        schedulingService.execute();

        await()
                .atMost(30, TimeUnit.SECONDS)
                .until(() -> submittedStrategies.size() == specificationOfStrategies.size());
        assertThat(submittedStrategies).hasSameElementsAs(
                specificationOfStrategies.stream()
                        .map(SpecificationOfStrategy::getId)
                        .map(id -> new StringValue(id, String.valueOf(id)))
                        .toList()
        );
    }
}