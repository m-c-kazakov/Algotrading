package com.finance.strategyGeneration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.config.configurationProperties.KafkaConfigurationProperties;
import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;
import com.finance.strategyGeneration.service.broker.DataProducer;
import com.finance.strategyGeneration.service.broker.JsonSerializer;
import com.finance.strategyGeneration.service.broker.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.finance.strategyGeneration.service.broker.JsonSerializer.OBJECT_MAPPER;
import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.RETRIES_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Slf4j
@Configuration
public class KafkaConfig {

    @Bean
    public DataProducer dataSender(KafkaProducer<Long, SpecificationOfStrategyDto> producer, KafkaConfigurationProperties properties) {
        return new Producer(producer, properties.getTopic_name(), stringValue -> {
        });
    }

    @Bean
    public KafkaProducer<Long, SpecificationOfStrategyDto> kafkaProducer(KafkaConfigurationProperties properties) {
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

        KafkaProducer<Long, SpecificationOfStrategyDto> kafkaProducer = new KafkaProducer<>(props);

        var shutdownHook = new Thread(() -> {
            log.info("closing kafka producer");
            kafkaProducer.close();
        });
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        return kafkaProducer;
    }
}
