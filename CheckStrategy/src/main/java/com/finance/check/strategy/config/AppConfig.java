package com.finance.check.strategy.config;

import com.finance.check.strategy.service.StrategyManager;
import com.finance.check.strategy.service.StrategyManagerImpl;
import com.finance.check.strategy.service.broker.DataConsumer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {

    @Bean("strategyManagerThreadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor(@Value("${app.checkStrategy.threadPoolSize}") Integer threadPoolSize) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                threadPoolSize,
                threadPoolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        Runtime.getRuntime().addShutdownHook(new Thread(threadPoolExecutor::shutdown));
        return threadPoolExecutor;
    }

    @Bean
    public StrategyManager strategyManager(DataConsumer kafkaDataConsumer,
                                           @Qualifier("strategyManagerThreadPoolExecutor") ThreadPoolExecutor executor) {
        return new StrategyManagerImpl(kafkaDataConsumer, executor);
    }
}
