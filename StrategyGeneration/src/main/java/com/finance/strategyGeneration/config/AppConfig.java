package com.finance.strategyGeneration.config;

import com.finance.strategyGeneration.mapper.SpecificationOfStrategyMapper;
import com.finance.strategyGeneration.service.GeneticAlgorithmManager;
import com.finance.strategyGeneration.service.GeneticAlgorithmManagerImpl;
import com.finance.strategyGeneration.service.broker.producer.DataProducer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Bean
    public GeneticAlgorithmManager geneticAlgorithmManager(
            GeneticAlgorithm geneticAlgorithm,
            SpecificationOfStrategyMapper mapper,
            DataProducer dataProducer
    ) {

        return new GeneticAlgorithmManagerImpl(geneticAlgorithm, mapper, Executors.newSingleThreadExecutor(), dataProducer);
    }
}
