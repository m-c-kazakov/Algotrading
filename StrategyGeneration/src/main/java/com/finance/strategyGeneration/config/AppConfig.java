package com.finance.strategyGeneration.config;

import com.finance.strategyGeneration.mapper.SpecificationOfStrategyMapper;
import com.finance.strategyGeneration.service.ManagerOfSendingForVerification;
import com.finance.strategyGeneration.service.ManagerOfSendingForVerificationImpl;
import com.finance.strategyGeneration.service.SpecificationOfStrategyService;
import com.finance.strategyGeneration.service.broker.producer.DataProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {

    @Bean
    public Executor executor() {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 48, 1000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        Runtime.getRuntime().addShutdownHook(new Thread(threadPoolExecutor::shutdown));
        return threadPoolExecutor;
    }

    @Bean
    public ManagerOfSendingForVerification geneticAlgorithmManager(
            SpecificationOfStrategyMapper mapper,
            SpecificationOfStrategyService specificationOfStrategyService,
            Executor executor,
            DataProducer dataProducer
    ) {

        return new ManagerOfSendingForVerificationImpl(mapper, specificationOfStrategyService, executor, dataProducer);
    }
}
