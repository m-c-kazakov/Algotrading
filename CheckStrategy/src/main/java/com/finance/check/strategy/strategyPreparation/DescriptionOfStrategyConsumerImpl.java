package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.feign.GeneticAlgorithmFeign;
import com.finance.check.strategy.service.StrategyExecutor;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionOfStrategyConsumerImpl implements DescriptionOfStrategyConsumer {

    StrategyPreparationManager strategyPreparationManager;
    GeneticAlgorithmFeign geneticAlgorithmFeign;


    // TODO Подключить Kafka

    @Override
    public void receive(DescriptionOfStrategy descriptionOfStrategy) {

        StrategyExecutor strategyExecutor = strategyPreparationManager.prepare(descriptionOfStrategy);

        StatisticsDataOfStrategy statisticsDataOfStrategy = strategyExecutor.perform();

        geneticAlgorithmFeign.saveStatisticsDataOfStrategy(statisticsDataOfStrategy);
    }
}
