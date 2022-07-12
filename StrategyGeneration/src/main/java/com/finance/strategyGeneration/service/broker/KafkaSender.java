package com.finance.strategyGeneration.service.broker;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaSender implements DataSender {

    KafkaProducer<Long, StringValue> producer;
    String topicName;
    Consumer<StringValue> callBack;

    @Override
    public void dataHandler(StringValue value) {
        try {
            producer.send(new ProducerRecord<>(topicName, value.id(), value),
                    (metadata, exception) -> {
                        if (exception != null) {
                            log.error("message wasn't sent", exception);
                        } else {
                            log.info("message id:{} was sent, offset:{}", value.id(), metadata.offset());
                        }
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void dataHandler(List<StringValue> values) {
        values.forEach(this::dataHandler);
    }
}
