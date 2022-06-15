package com.finance.check.strategy.dealManagement.collector;

import com.finance.check.strategy.dataHolder.StatisticsDataOfStrategy;
import org.springframework.stereotype.Component;

@Component
public class StatisticsInformationCollectorImpl implements StatisticsInformationCollector {
    @Override
    public void toCollect(StatisticsDataOfStrategy statisticsDataOfStrategy, long startScore, long oldStateOfScore,
                          long newStateOfScore) {
        boolean isDealProfitable = newStateOfScore > oldStateOfScore;

        increaseStatisticsOfEndDeal(statisticsDataOfStrategy, isDealProfitable);

        saveAveragePercentValueInformationOfDrawdown(statisticsDataOfStrategy, isDealProfitable, oldStateOfScore,
                newStateOfScore);

        saveMaximumValueOfScoreState(statisticsDataOfStrategy, newStateOfScore);

        saveMaximumPercentDrawdownFromStartScore(statisticsDataOfStrategy, startScore, newStateOfScore);
    }

    public void saveMaximumPercentDrawdownFromStartScore(StatisticsDataOfStrategy statisticsDataOfStrategy,
                                                         long startScore, long newStateOfScore) {
        if (newStateOfScore < startScore) {
            long oldPercentDrawdown = statisticsDataOfStrategy.getMaximumPercentDrawdownFromStartScore();
            long newPercentDrawdown = (newStateOfScore * 100) / startScore;
            if (oldPercentDrawdown > newPercentDrawdown) {
                statisticsDataOfStrategy.setMaximumPercentDrawdownFromStartScore(newPercentDrawdown);
            }
        }
    }

    public void saveMaximumValueOfScoreState(StatisticsDataOfStrategy statisticsDataOfStrategy, long newStateOfScore) {
        if (statisticsDataOfStrategy.getMaximumValueFromScore() < newStateOfScore) {
            statisticsDataOfStrategy.setMaximumValueFromScore(newStateOfScore);
        }
    }

    public void saveAveragePercentValueInformationOfDrawdown(StatisticsDataOfStrategy statisticsDataOfStrategy,
                                                             boolean isDealProfitable, long oldStateOfScore,
                                                             long newStateOfScore) {
        if (!isDealProfitable) {
            long percentDrawdownOfTheScore = newStateOfScore * 100 / oldStateOfScore;
            long averagePercentDrawdownFromScore = statisticsDataOfStrategy.getAveragePercentDrawdownOfScore() == 0 ?
                    percentDrawdownOfTheScore :
                    (statisticsDataOfStrategy.getAveragePercentDrawdownOfScore() + percentDrawdownOfTheScore) / 2;
            statisticsDataOfStrategy.setAveragePercentDrawdownOfScore(averagePercentDrawdownFromScore);
        }
    }

    public void increaseStatisticsOfEndDeal(StatisticsDataOfStrategy statisticsDataOfStrategy,
                                            boolean isDealProfitable) {
        if (isDealProfitable) {
            statisticsDataOfStrategy.incrementWinningDeal();
        } else {
            statisticsDataOfStrategy.incrementLosingDeal();
        }
    }
}
