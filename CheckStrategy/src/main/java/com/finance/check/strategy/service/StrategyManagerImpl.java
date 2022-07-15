package com.finance.check.strategy.service;


import com.finance.check.strategy.service.broker.DataConsumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;


@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyManagerImpl implements StrategyManager {

    DataConsumer kafkaDataConsumer;
    Integer theBorderForGettingNewDescriptionOfStrategy;
    ThreadPoolExecutor executor;

    @Override
    @Scheduled(fixedDelay = 1500)
    public void execute() {
        log.info("Проверка необходимости получения новых стратегий из Kafka result={}; TaskCount={}; theBorderForGettingNewDescriptionOfStrategy={}", executor.getTaskCount() < theBorderForGettingNewDescriptionOfStrategy, executor.getTaskCount(), theBorderForGettingNewDescriptionOfStrategy);
        if (executor.getTaskCount() < theBorderForGettingNewDescriptionOfStrategy) {
            Stream.iterate(0, i -> i < Math.max(executor.getTaskCount() / 3, 2), i -> i + 1)
                    .forEach(descriptionOfStrategyConsumer -> executor.execute(kafkaDataConsumer::poll));
        }

    }
}
