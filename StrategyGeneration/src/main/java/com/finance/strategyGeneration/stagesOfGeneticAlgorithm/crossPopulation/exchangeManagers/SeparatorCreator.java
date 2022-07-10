package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class SeparatorCreator {

    public int execute(List<InformationOfIndicator> firstIndicators, List<InformationOfIndicator> secondIndicators) {

        int bound = Math.min(firstIndicators.size(), secondIndicators.size());
        return bound <= 1 ? 1 : ThreadLocalRandom.current().nextInt(1, bound);
    }
}
