package com.finance.check.strategy.service.broker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.check.strategy.config.configurationProperties.KafkaConfigurationProperties;
import com.finance.check.strategy.dto.CandlesInformationDto;
import com.finance.check.strategy.dto.DescriptionOfStrategyDto;
import com.finance.check.strategy.dto.IndicatorDto;
import com.finance.check.strategy.intagration.JsonSerializer;
import com.finance.check.strategy.intagration.KafkaTestBased;
import com.finance.check.strategy.mapper.DescriptionOfStrategyMapper;
import com.finance.check.strategy.strategyPreparation.StrategyVerificationManager;
import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

import static com.finance.check.strategy.service.broker.JsonDeserializer.OBJECT_MAPPER;
import static com.finance.check.strategy.service.broker.JsonDeserializer.TYPE_REFERENCE;
import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.GROUP_INSTANCE_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.*;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@EnableConfigurationProperties(value = KafkaConfigurationProperties.class)
class KafkaDataConsumerTest extends KafkaTestBased {

    @MockBean
    StrategyVerificationManager strategyVerificationManager;

    @Autowired
    KafkaDataConsumer kafkaDataConsumer;

    @Autowired
    DescriptionOfStrategyMapper mapper;

    @Autowired
    KafkaConfigurationProperties properties;

    private void putValuesToKafka(List<DescriptionOfStrategyDto> descriptionOfStrategyDtos) {
        Properties props = new Properties();
        props.put(CLIENT_ID_CONFIG, "StrategyGenerationProducer");
        props.put(BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrap_servers_config());
        props.put(ACKS_CONFIG, "0");
        props.put(LINGER_MS_CONFIG, 1);
        props.put(KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(OBJECT_MAPPER, new ObjectMapper());

        try (var kafkaProducer = new KafkaProducer<Long, DescriptionOfStrategyDto>(props)) {
            for (var value : descriptionOfStrategyDtos) {
                kafkaProducer.send(new ProducerRecord<>(properties.getTopic_name(), value.getId(), value));
            }
        }
    }

    @Test
    void poll() {

        List<DescriptionOfStrategyDto> descriptionOfStrategyDtos =
                Stream.iterate(0, i -> i < 3, i -> i + 1).map(Long::valueOf).map(this::create).toList();
        putValuesToKafka(descriptionOfStrategyDtos);

        kafkaDataConsumer.poll();


        Mockito.verify(strategyVerificationManager, Mockito.times(3)).receive(any());
    }

    private DescriptionOfStrategyDto create(Long id) {
        CandlesInformationDto candlesInformationDto = CandlesInformationDto
                .builder()
                .currencyPair(CurrencyPair.EURUSD)
                .timeFrame(TimeFrame.M1)
                .build();
        return DescriptionOfStrategyDto
                .builder()
                .id(id)
                .startScore(100L)
                .acceptableRisk(3)
                .sumOfDealType(SumOfDealType.PERCENT_OF_SCORE)
                .sumOfDealConfigurationData(Map.of())
                .stopLossType(StopLossType.FIXED_STOP_LOSS)
                .stopLossConfigurationData(Map.of())
                .trailingStopType(TrailingStopType.FIXED_TRAILING_STOP_TYPE)
                .trailingStopConfigurationData(Map.of())
                .takeProfitType(TakeProfitType.FIXED_TAKE_PROFIT)
                .takeProfitConfigurationData(Map.of())
                .typeOfDeal(TypeOfDeal.BUY)
                .informationOfCandles(candlesInformationDto)
                .descriptionToOpenADeal(List.of(IndicatorDto.builder()
                        .indicatorType(IndicatorType.SMA)
                        .informationOfCandles(candlesInformationDto)
                        .parameters(Map.of())
                        .build()))
                .descriptionToCloseADeal(List.of())
                .build();
    }

    @TestConfiguration
    static class DataConsumerTestConfiguration {



        @Bean
        public KafkaDataConsumer dataConsumer(
                KafkaConsumer<Long, DescriptionOfStrategyDto> kafkaConsumer,
                DescriptionOfStrategyMapper mapper,
                StrategyVerificationManager strategyVerificationManager,
                @Qualifier("strategyManagerThreadPoolExecutor") ThreadPoolExecutor threadPoolExecutor) {

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
            props.put(MAX_POLL_RECORDS_CONFIG, 3);
            props.put(MAX_POLL_INTERVAL_MS_CONFIG, 300);

            KafkaConsumer<Long, DescriptionOfStrategyDto> kafkaConsumer = new KafkaConsumer<>(props);
            kafkaConsumer.subscribe(Collections.singletonList(properties.getTopic_name()));
            return kafkaConsumer;
        }
    }
}