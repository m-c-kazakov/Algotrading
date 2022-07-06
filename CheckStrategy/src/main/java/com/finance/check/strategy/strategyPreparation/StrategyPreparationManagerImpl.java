package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.service.StrategyExecutorImpl;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyPreparationManagerImpl implements StrategyPreparationManager {

    StrategyExecutorConfiguration strategyExecutorConfiguration;
    DataOfStrategyGeneratorService dataOfStrategyGeneratorService;

    @Override
    public StrategyExecutorImpl prepare(DescriptionOfStrategy descriptionOfStrategy) {

        DescriptionOfStrategy descriptionOfStrategyWithCandleAndIndicatorData = dataOfStrategyGeneratorService.generateDataOfIndicators(descriptionOfStrategy);

        StatisticsDataOfStrategy statisticsDataOfStrategy = StatisticsDataOfStrategy.builder()
                .specificationOfStrategyId(descriptionOfStrategyWithCandleAndIndicatorData.getId())
                .score(descriptionOfStrategyWithCandleAndIndicatorData.getStartScore())
                .valueOfAcceptableRisk(generateValueOfAcceptableRisk(descriptionOfStrategyWithCandleAndIndicatorData))
                .build();

        return strategyExecutorConfiguration.configurate(descriptionOfStrategyWithCandleAndIndicatorData,
                statisticsDataOfStrategy);
    }

    private int generateValueOfAcceptableRisk(DescriptionOfStrategy descriptionOfStrategy) {
        return (int) (descriptionOfStrategy.getStartScore() * descriptionOfStrategy.getAcceptableRisk() / 100);
    }

}
