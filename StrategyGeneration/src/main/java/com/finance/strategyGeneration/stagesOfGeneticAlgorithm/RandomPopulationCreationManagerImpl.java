package com.finance.strategyGeneration.stagesOfGeneticAlgorithm;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyGeneration.random.RandomStrategyParams;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomPopulationCreationManagerImpl implements RandomPopulationCreationManager {
    static long startScore = 1000; // TODO Вынести в Property
    static long acceptableRisk = 20; // TODO Вынести в Property

    static List<TypeOfDeal> typeOfDeals = List.of(TypeOfDeal.values());

    List<RandomStrategyParams> randomStrategyParams;

    @Override
    public DescriptionOfStrategy execute() {

        DescriptionOfStrategy.DescriptionOfStrategyBuilder dataOfStrategyBuilder = DescriptionOfStrategy.builder()
                .startScore(startScore)
                .acceptableRisk(acceptableRisk)
                .typeOfDeal(getTypeOfDeal());

        randomStrategyParams.forEach(randomStrategyParam -> randomStrategyParam.add(dataOfStrategyBuilder));
        return dataOfStrategyBuilder.build();
    }

    private TypeOfDeal getTypeOfDeal() {
        return typeOfDeals.get(ThreadLocalRandom.current()
                .nextInt(typeOfDeals.size()));
    }
}
