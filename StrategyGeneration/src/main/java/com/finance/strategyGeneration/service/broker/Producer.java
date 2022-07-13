package com.finance.strategyGeneration.service.broker;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
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
public class Producer implements DataProducer {

    KafkaProducer<Long, SpecificationOfStrategy> producer;
    String topicName;
    Consumer<SpecificationOfStrategy> callBack;

    @Override
    public void dataHandler(SpecificationOfStrategy specificationOfStrategy) {
        try {
            producer.send(new ProducerRecord<>(topicName, specificationOfStrategy.getId(), specificationOfStrategy),
                    (metadata, exception) -> {
                        if (exception != null) {
                            log.error("message wasn't sent", exception);
                        } else {
                            log.info("message id:{} was sent, offset:{}", specificationOfStrategy.getId(), metadata.offset());
                            callBack.accept(specificationOfStrategy);
                        }
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void dataHandler(List<SpecificationOfStrategy> values) {
        values.forEach(this::dataHandler);
    }
}
