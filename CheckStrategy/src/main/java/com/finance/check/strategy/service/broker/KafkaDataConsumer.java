package com.finance.check.strategy.service.broker;

import com.finance.check.strategy.dto.DescriptionOfStrategyDto;
import com.finance.check.strategy.mapper.DescriptionOfStrategyMapper;
import com.finance.check.strategy.strategyPreparation.StrategyVerificationManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaDataConsumer implements DataConsumer {

    KafkaConsumer<Long, DescriptionOfStrategyDto> kafkaConsumer;
    Duration timeout;
    DescriptionOfStrategyMapper mapper;
    StrategyVerificationManager strategyVerificationManager;
    ThreadPoolExecutor executor;


    @Override
    public synchronized void poll() {
        log.info("poll records");
        ConsumerRecords<Long, DescriptionOfStrategyDto> records = kafkaConsumer.poll(timeout);
        log.info("polled records.counter:{}", records.count());

        StreamSupport.stream(records.spliterator(), false)
                .map(ConsumerRecord::value)
                .map(mapper::mapTo)
                .forEach(descriptionOfStrategy -> executor.execute(() -> strategyVerificationManager.receive(descriptionOfStrategy)));
    }
}
