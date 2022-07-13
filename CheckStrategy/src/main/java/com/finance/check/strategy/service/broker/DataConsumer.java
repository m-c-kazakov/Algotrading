package com.finance.check.strategy.service.broker;

import com.finance.check.strategy.dto.DescriptionOfStrategyDto;
import com.finance.check.strategy.mapper.DescriptionOfStrategyMapper;
import com.finance.check.strategy.strategyPreparation.DescriptionOfStrategyConsumer;
import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataConsumer {

    KafkaConsumer<Long, DescriptionOfStrategyDto> kafkaConsumer;
    Duration timeout;
    Consumer<DescriptionOfStrategyDto> callBack;
    DescriptionOfStrategyConsumer descriptionOfStrategyConsumer;
    Executor executor;
    DescriptionOfStrategyMapper mapper;

//    @Scheduled(fixedDelay = 2000)
    public void poll() {
        log.info("poll records");
        ConsumerRecords<Long, DescriptionOfStrategyDto> records = kafkaConsumer.poll(timeout);
        log.info("polled records.counter:{}", records.count());
        for (ConsumerRecord<Long, DescriptionOfStrategyDto> kafkaRecord : records) {
            try {
                executor.execute(() -> {
                    DescriptionOfStrategyDto descriptionOfStrategyDto = kafkaRecord.value();
                    DescriptionOfStrategy descriptionOfStrategy = mapper.mapTo(descriptionOfStrategyDto);
                    descriptionOfStrategyConsumer.receive(descriptionOfStrategy);
                });
                callBack.accept(kafkaRecord.value());
            } catch (Exception ex) {
                log.error("can't parse record:{}", kafkaRecord, ex);
            }
        }
    }
}
