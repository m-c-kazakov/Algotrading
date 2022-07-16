package com.finance.strategyGeneration.service.broker.producer;

import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;
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
public class KafkaDataProducer implements DataProducer {

    KafkaProducer<Long, SpecificationOfStrategyDto> producer;
    String topicName;
    Consumer<SpecificationOfStrategyDto> callBack;

    @Override
    public void dataHandler(SpecificationOfStrategyDto specificationOfStrategy) {
        try {
            producer.send(new ProducerRecord<>(topicName, specificationOfStrategy.getId(), specificationOfStrategy),
                    (metadata, exception) -> {
                        if (exception != null) {
                            log.error("message wasn't sent", exception);
                        } else {
                            log.info("message id:{} was sent, topicName={} offset:{}",
                                    specificationOfStrategy.getId(),
                                    metadata.topic(),
                                    metadata.offset());
                            callBack.accept(specificationOfStrategy);
                        }
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void dataHandler(List<SpecificationOfStrategyDto> values) {
        values.forEach(this::dataHandler);
    }
}
