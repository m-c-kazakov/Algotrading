package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators.generatorRandomParametersByIndicatorType.GeneratorRandomParametersByIndicatorType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneratorRandomParametersOfIndicatorImpl implements GeneratorRandomParametersOfIndicator {

    Map<String, GeneratorRandomParametersByIndicatorType> randomParameters;

    @Override
    public Map<String, String> getRandomParametersByIndicatorType(@NonNull IndicatorType indicatorType) {
        return randomParameters.get(indicatorType.name())
                .get();
    }
}
