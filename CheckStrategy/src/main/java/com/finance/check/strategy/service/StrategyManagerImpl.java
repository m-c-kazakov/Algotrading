package com.finance.check.strategy.service;


import com.finance.check.strategy.service.broker.DataConsumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ThreadPoolExecutor;


@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyManagerImpl implements StrategyManager {

    DataConsumer kafkaDataConsumer;
    Integer theBorderForGettingNewDescriptionOfStrategy;
    ThreadPoolExecutor executor;

    @Override
    @Scheduled(fixedDelay = 5000)
    public void execute() {
        // TODO придумать как брать стратегии на исполнение только в случае способности брать дополнительные задачи
        kafkaDataConsumer.poll();

    }
}
