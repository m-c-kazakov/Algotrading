package com.finance.check.strategy.dealManagement.closingDealManagement;

import com.finance.check.strategy.dealManagement.collector.StatisticsInformationCollector;
import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClosingDealManagerImpl implements ClosingDealManager {

    StatisticsInformationCollector statisticsInformationCollector;

    @Override
    public void execute(DataOfStrategy dataOfStrategy, int cursor, DataOfDeal dataOfDeal,
                        StatisticsDataOfStrategy statisticsDataOfStrategy) {

        int pipResult = createPipResult(dataOfStrategy.getTypeOfDeal(), dataOfStrategy.getClosingPrice(cursor),
                dataOfDeal.getOpeningPrice());
        long resultOfDeal = pipResult * dataOfDeal.getSumOfDeal();

        long oldStateOfScore = statisticsDataOfStrategy.getScore();
        long newStateOfScore = oldStateOfScore + resultOfDeal;
        statisticsDataOfStrategy.setScore(newStateOfScore);

        statisticsInformationCollector.toCollect(statisticsDataOfStrategy, dataOfStrategy.getStartScore(),
                oldStateOfScore, newStateOfScore);
    }

    private int createPipResult(TypeOfDeal typeOfDeal, int closingPrice, int openingPrice) {
        if (typeOfDeal == TypeOfDeal.BUY) {
            return closingPrice - openingPrice;
        } else if (typeOfDeal == TypeOfDeal.SELL) {
            return openingPrice - closingPrice;
        } else {
            throw new RuntimeException("Не корректно определен TypeOfDeal. Текущее значение=" + typeOfDeal);
        }
    }
}
