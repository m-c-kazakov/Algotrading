package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.indicatorMutation;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators.generatorRandomParametersByIndicatorType.GeneratorRandomParametersByIndicatorType;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorToOpeningDealParameterMutation implements Mutation {
    Map<String, GeneratorRandomParametersByIndicatorType> randomParameters;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        List<Indicator> indicators = new ArrayList<>(parentDescriptionOfStrategy.getIndicatorsDescriptionToOpenADeal());

        int bound = Math.max(indicators.size() / 2, 1);
        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current()
                .nextInt(bound), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current()
                    .nextInt(indicators.size());

            Indicator indicatorForReplace = indicators.get(replacedIndex).copy();

            IndicatorType indicatorType = indicatorForReplace.getIndicatorType();

            List<String> namesOfIndicatorParameters = indicatorType.getNamesOfIndicatorParameters();

            String keyOfIndicatorParameter = namesOfIndicatorParameters.get(ThreadLocalRandom.current()
                    .nextInt(namesOfIndicatorParameters.size()));

            Map<String, Supplier<String>> mapWithRandomMutationSuppliers = randomParameters
                    .get(indicatorType.name())
                    .getParametersSupplierMap();

            Supplier<String> supplierToMutationParameter = mapWithRandomMutationSuppliers.get(keyOfIndicatorParameter);

            Map<String, String> parametersOfIndicator = indicatorForReplace.getParameters();
            parametersOfIndicator.put(keyOfIndicatorParameter, supplierToMutationParameter.get());
            indicatorForReplace.withParameters(parametersOfIndicator);


            indicators.set(replacedIndex, indicatorForReplace);
        }

        DescriptionOfStrategy descriptionOfStrategyAfterMutation = parentDescriptionOfStrategy.withDescriptionToOpenADeal(
                indicators);

        return Stream.of(parentDescriptionOfStrategy, descriptionOfStrategyAfterMutation);
    }

}
