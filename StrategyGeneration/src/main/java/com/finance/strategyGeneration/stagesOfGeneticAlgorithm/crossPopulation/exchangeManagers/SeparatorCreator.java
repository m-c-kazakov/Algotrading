package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class SeparatorCreator {

    public int execute(List<Indicator> firstIndicators, List<Indicator> secondIndicators) {

        int bound = Math.min(firstIndicators.size(), secondIndicators.size());
        return bound <= 1 ? 1 : ThreadLocalRandom.current().nextInt(1, bound);
    }
}
