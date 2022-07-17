package com.finance.strategyGeneration.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.config.configurationProperties.KafkaConsumerConfigurationProperties;
import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;
import com.finance.strategyGeneration.service.broker.consumer.JsonDeserializer;
import com.finance.strategyGeneration.service.broker.consumer.KafkaDataConsumer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static com.finance.strategyGeneration.service.broker.consumer.JsonDeserializer.OBJECT_MAPPER;
import static com.finance.strategyGeneration.service.broker.consumer.JsonDeserializer.TYPE_REFERENCE;
import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.GROUP_INSTANCE_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Slf4j
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public KafkaDataConsumer dataConsumer(KafkaConsumer<Long, SpecificationOfStrategyDto> kafkaConsumer, KafkaConsumerConfigurationProperties properties) {
        return new KafkaDataConsumer(kafkaConsumer, Duration.ofMillis(properties.getTimeout_duration()));
    }

    @Bean
    @SneakyThrows
    public KafkaConsumer<Long, SpecificationOfStrategyDto> kafkaConsumer(KafkaConsumerConfigurationProperties properties) {

        var props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrap_servers_config());
        props.put(GROUP_ID_CONFIG, properties.getGroup_id_config());
        props.put(GROUP_INSTANCE_ID_CONFIG, properties.getGroup_instance_id_config());
        props.put(ENABLE_AUTO_COMMIT_CONFIG, properties.getEnable_auto_commit_config());
        props.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, properties.getAuto_commit_interval_ms_config());
        props.put(AUTO_OFFSET_RESET_CONFIG, properties.getAuto_offset_reset_config());
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(OBJECT_MAPPER, new ObjectMapper());
        props.put(TYPE_REFERENCE, new TypeReference<SpecificationOfStrategyDto>() {
        });
        props.put(MAX_POLL_RECORDS_CONFIG, properties.getMax_poll_records_config());
        props.put(MAX_POLL_INTERVAL_MS_CONFIG, properties.getMax_poll_interval_ms_config());

        KafkaConsumer<Long, SpecificationOfStrategyDto> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singletonList(properties.getTopic_name()));

        var shutdownHook = new Thread(() -> {
            log.info("closing kafka consumer");
            kafkaConsumer.close();
        });
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        return kafkaConsumer;
    }
}
