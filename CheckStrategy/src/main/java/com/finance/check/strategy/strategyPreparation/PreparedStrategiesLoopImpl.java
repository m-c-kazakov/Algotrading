package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.StrategyExecutor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PreparedStrategiesLoopImpl implements PreparedStrategiesLoop {


    static ArrayBlockingQueue<StrategyExecutor> strategies = new ArrayBlockingQueue<>(1000);
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public void addStrategy(StrategyExecutor strategy) {
        strategies.offer(strategy);
    }


    @PostConstruct
    private void startEventLoop() {
        while (true) {
            singleThreadExecutor.execute(() -> {
                try {
                    Runnable runnable = strategies.take();
                    executorService.execute(runnable);
                } catch (Exception e) {
                    log.error("Ошибка при запуске стратегии", e);
                }
            });
        }
    }

}
