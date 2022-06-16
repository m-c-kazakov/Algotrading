package com.finance.strategyGeneration.mutation.indicatorMutation;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
public class RemoveIndicatorOpeningDeal implements Mutation {

    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {
        List<Indicator> indicators = parentDataOfStrategy.getIndicatorsDescriptionToOpenADeal();

        int numberOfDeletedItems = indicators.size() / 2;

        for (int i = 0; i < numberOfDeletedItems; i++) {

            if (indicators.size() / 2 >= 1) {
                int removeIndex = ThreadLocalRandom.current()
                        .nextInt(indicators.size());
                indicators.remove(removeIndex);
            }
        }


        DataOfStrategy dataOfStrategyAfterMutation = parentDataOfStrategy.withDescriptionToOpenADeal(
                new DescriptionToOpenADeal(indicators));

        return Stream.of(parentDataOfStrategy, dataOfStrategyAfterMutation);
    }
}
