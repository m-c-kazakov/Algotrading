package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.feign.GeneticAlgorithmFeign;
import com.finance.check.strategy.service.StrategyExecutor;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyVerificationManagerImpl implements StrategyVerificationManager {

    StrategyPreparationManager strategyPreparationManager;
    GeneticAlgorithmFeign geneticAlgorithmFeign;

    @Override
    public void receive(DescriptionOfStrategy descriptionOfStrategy) {
        log.debug("Запуск стратегии с id={} на проверку.", descriptionOfStrategy.getId());

        StrategyExecutor strategyExecutor = strategyPreparationManager.prepare(descriptionOfStrategy);

        StatisticsDataOfStrategy statisticsDataOfStrategy = strategyExecutor.perform();

        geneticAlgorithmFeign.saveStatisticsDataOfStrategy(statisticsDataOfStrategy);
    }
}
