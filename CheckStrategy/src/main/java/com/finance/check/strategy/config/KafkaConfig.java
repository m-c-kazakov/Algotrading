package com.finance.check.strategy.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.check.strategy.config.configurationProperties.KafkaConfigurationProperties;
import com.finance.check.strategy.dto.DescriptionOfStrategyDto;
import com.finance.check.strategy.mapper.DescriptionOfStrategyMapper;
import com.finance.check.strategy.service.broker.JsonDeserializer;
import com.finance.check.strategy.service.broker.KafkaDataConsumer;
import com.finance.check.strategy.strategyPreparation.StrategyVerificationManager;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

import static com.finance.check.strategy.service.broker.JsonDeserializer.OBJECT_MAPPER;
import static com.finance.check.strategy.service.broker.JsonDeserializer.TYPE_REFERENCE;
import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.GROUP_INSTANCE_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaDataConsumer dataConsumer(KafkaConsumer<Long, DescriptionOfStrategyDto> kafkaConsumer,
                                          DescriptionOfStrategyMapper mapper,
                                          StrategyVerificationManager strategyVerificationManager,
                                          @Qualifier("strategyManagerThreadPoolExecutor") ThreadPoolExecutor threadPoolExecutor) {
        // TODO вынести параметры в property
        Duration timeout = Duration.ofMillis(2_000);
        return new KafkaDataConsumer(kafkaConsumer, timeout, mapper, strategyVerificationManager, threadPoolExecutor);
    }

    @Bean
    @SneakyThrows
    public KafkaConsumer<Long, DescriptionOfStrategyDto> kafkaConsumer(KafkaConfigurationProperties properties) {

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
        props.put(TYPE_REFERENCE, new TypeReference<DescriptionOfStrategyDto>() {
        });
        props.put(MAX_POLL_RECORDS_CONFIG, properties.getMax_poll_records_config());
        props.put(MAX_POLL_INTERVAL_MS_CONFIG, properties.getMax_poll_interval_ms_config());

        KafkaConsumer<Long, DescriptionOfStrategyDto> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singletonList(properties.getTopic_name()));
        return kafkaConsumer;
    }
}
