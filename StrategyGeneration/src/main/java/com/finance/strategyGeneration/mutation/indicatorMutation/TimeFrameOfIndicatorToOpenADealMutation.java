package com.finance.strategyGeneration.mutation.indicatorMutation;

import com.finance.dataHolder.DataOfStrategy;
import com.finance.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.mutation.Mutation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeFrameOfIndicatorToOpenADealMutation implements Mutation {

    static List<TimeFrame> TIME_FRAMES = List.of(TimeFrame.values());

    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {

        List<Indicator> indicators = parentDataOfStrategy.getIndicatorsDescriptionToOpenADeal();

        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current()
                .nextInt(indicators.size() / 2), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current()
                    .nextInt(indicators.size());
            Indicator indicator = indicators.get(replacedIndex)
                    .copy();

            TimeFrame timeFrame = TIME_FRAMES.get(ThreadLocalRandom.current()
                    .nextInt(TIME_FRAMES.size()));

            indicators.set(replacedIndex, indicator.withTimeFrame(timeFrame));
        }

        DataOfStrategy dataOfStrategyAfterMutation = parentDataOfStrategy.withDescriptionToOpenADeal(
                new DescriptionToOpenADeal(indicators));

        return Stream.of(parentDataOfStrategy, dataOfStrategyAfterMutation);
    }
}
