package com.finance.createIndicatorData.service.converterToDataOfIndicator.indicators;

import com.finance.createIndicatorData.service.converterToDataOfIndicator.BitwiseShift;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SMAIndicatorImpl implements SMAIndicator {

    static Map<TypeOfDeal, RuleForCreatingSmaSolution> rulesForCreationSmaSolution = Map.of(
            TypeOfDeal.BUY, (candlesData, smaData, index) -> candlesData.get(index) > smaData.get(index),
            TypeOfDeal.SELL, (candlesData, smaData, index) -> candlesData.get(index) < smaData.get(index)

    );

    @Override
    public int calculateSma(int period, List<Integer> dataOfCandle, int cursor) {
        List<Integer> subArray = dataOfCandle.subList(cursor - period + 1, cursor + 1);
        Integer sum = subArray.stream()
                .reduce(0, Math::addExact);
        return sum / period;
    }

    @Override
    public List<Integer> sequentialGenerationSmaResult(int period, List<Integer> dataOfCandle) {
        List<Integer> smaResult = new ArrayList<>(dataOfCandle.size());
        for (int cursor = period; cursor < dataOfCandle.size(); cursor++) {
            int sma = calculateSma(period, dataOfCandle, cursor);
            smaResult.add(sma);
        }

        return smaResult;
    }

    @Override
    public List<Integer> parallelGenerationSmaResult(int period, List<Integer> dataOfCandle) {
        // TODO реализовать параллельное выполнение. Сравнить скорость. https://www.baeldung.com/java-when-to-use-parallel-stream
        return List.of();
    }

    @Override
    public List<Integer> getDecision(TypeOfDeal typeOfDeal, Indicator indicator, List<Integer> dataOfCandles,
                                     List<Integer> smaResult) {
        Assert.state(dataOfCandles.size() != smaResult.size(),
                "Размер коллекции данных свечи не равен размеру коллекции данных индикатора.");

        int batchSize = indicator.getTimeFrame().getBatchSize();
        List<Integer> butchDecisionByBuy = new ArrayList<>();

        for (int cursorOfBatch = batchSize; cursorOfBatch < dataOfCandles.size(); cursorOfBatch += batchSize) {

            int baseCursor = cursorOfBatch - batchSize;
            int batchOfResult = rulesForCreationSmaSolution.get(typeOfDeal)
                    .execute(dataOfCandles, smaResult, baseCursor) ? 1 : 0;

            for (int cursorOfElement = baseCursor + 1; cursorOfElement < cursorOfBatch; cursorOfElement++) {
                if (rulesForCreationSmaSolution.get(typeOfDeal)
                        .execute(dataOfCandles, smaResult, cursorOfElement)) {
                    batchOfResult = BitwiseShift.addOne(batchOfResult);
                } else {
                    batchOfResult = BitwiseShift.addZero(batchOfResult);
                }
            }

            butchDecisionByBuy.add(batchOfResult);

        }

        return butchDecisionByBuy;
    }

    @FunctionalInterface
    interface RuleForCreatingSmaSolution {
        boolean execute(List<Integer> dataOfCandles, List<Integer> dataOfSma, int elementIndex);
    }
}
