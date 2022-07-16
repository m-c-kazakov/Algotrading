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
    ThreadPoolExecutor executor;

    @Override
    @Scheduled(fixedDelayString = "#{new Integer('${app.kafka.max_poll_interval_ms_config}')*10/100}")
    public void execute() {
        executor.execute(() -> {
            try {
                log.info(">> Запуск получения данных из Kafka");
                // TODO придумать как брать стратегии на исполнение только в случае способности брать дополнительные задачи
                kafkaDataConsumer.poll();
            } catch (Exception e) {
                log.error("Ошибка при получении данных из Kafka={}", e.getMessage(), e);
            }
        });
    }
}
