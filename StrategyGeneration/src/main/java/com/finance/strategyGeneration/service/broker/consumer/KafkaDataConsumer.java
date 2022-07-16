package com.finance.strategyGeneration.service.broker.consumer;

import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaDataConsumer implements DataConsumer {

    KafkaConsumer<Long, SpecificationOfStrategyDto> kafkaConsumer;
    Duration timeout;


    @Override
    public synchronized Integer poll() {
        log.info("poll records");
        ConsumerRecords<Long, SpecificationOfStrategyDto> records = kafkaConsumer.poll(timeout);
        log.info("polled records.counter: {}", records.count());
        return records.count();
    }
}
