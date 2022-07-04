package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.service.StrategyExecutor;
import com.finance.dataHolder.DataOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyPreparationManagerImpl {

    PreparedStrategiesLoop preparedStrategiesLoop;
    StrategyExecutorConfiguration strategyExecutorConfiguration;
    DataHolderForStrategies dataHolderForStrategies;


    public void prepare(DataOfStrategy dataOfStrategy) {

        StatisticsDataOfStrategy statisticsDataOfStrategy = StatisticsDataOfStrategy.builder()
                .strategyId(dataOfStrategy.getId())
                .score(dataOfStrategy.getStartScore())
                .valueOfAcceptableRisk(generateValueOfAcceptableRisk(dataOfStrategy))
                .build();


        DataOfStrategy readyDataOfStrategy = dataOfStrategy
                .withDataOfCandles(dataHolderForStrategies.getDataOfCandles(dataOfStrategy.getCandlesInformation()))
                .withDecisionToOpenADeal(
                        dataHolderForStrategies.getDecisionToOpenADeal(dataOfStrategy.getDescriptionToOpenADeal()))
                .withDecisionToCloseADeal(
                        dataHolderForStrategies.getDecisionToCloseADeal(dataOfStrategy.getDescriptionToCloseADeal()));

        StrategyExecutor strategyExecutor = strategyExecutorConfiguration.configurate(readyDataOfStrategy,
                statisticsDataOfStrategy);
        preparedStrategiesLoop.addStrategy(strategyExecutor);


    }

    private int generateValueOfAcceptableRisk(DataOfStrategy dataOfStrategy) {
        return (int) (dataOfStrategy.getStartScore() * dataOfStrategy.getAcceptableRisk() / 100);
    }

}
