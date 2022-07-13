package com.finance.check.strategy.service.broker;

import com.finance.check.strategy.strategyPreparation.DescriptionOfStrategyConsumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataConsumer {

    KafkaConsumer<Long, StringValue> kafkaConsumer;
    Duration timeout;
    Consumer<StringValue> callBack;
    DescriptionOfStrategyConsumer descriptionOfStrategyConsumer;
    Executor executor;

    @Scheduled(fixedDelay = 2000)
    public void poll() {
        log.info("poll records");
        ConsumerRecords<Long, StringValue> records = kafkaConsumer.poll(timeout);
        log.info("polled records.counter:{}", records.count());
        for (ConsumerRecord<Long, StringValue> kafkaRecord : records) {
            try {
                // TODO доделать
//                executor.execute(() -> descriptionOfStrategyConsumer.receive(kafkaRecord.value()));
                callBack.accept(kafkaRecord.value());
            } catch (Exception ex) {
                log.error("can't parse record:{}", kafkaRecord, ex);
            }
        }
    }
}
