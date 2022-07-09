package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators.generatorRandomParametersByIndicatorType;

import com.finance.strategyDescriptionParameters.TypeOfBar;
import com.finance.strategyDescriptionParameters.indicators.SmaParameters;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Component("SMA")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SmaGeneratorRandomParameters implements GeneratorRandomParametersByIndicatorType {

    List<SmaParameters> smaParameters = List.of(SmaParameters.values());
    List<TypeOfBar> typeOfBars = List.of(TypeOfBar.values());

    @Getter
    Map<String, Supplier<String>> parametersSupplierMap = new HashMap<>();

    {
        parametersSupplierMap.put(SmaParameters.CALCULATE_BY.name(),
                () -> String.valueOf(typeOfBars.get(ThreadLocalRandom.current()
                        .nextInt(typeOfBars.size()))));

        parametersSupplierMap.put(SmaParameters.PERIOD.name(), () -> String.valueOf(ThreadLocalRandom.current()
                .nextInt(1, 101)));
    }


    @Override
    public Map<String, String> get() {

        Map<String, String> randomParameters = new HashMap<>();

        smaParameters.forEach(
                smaParameter -> randomParameters.put(smaParameter.name(), parametersSupplierMap.get(smaParameter.name()).get()));


        return randomParameters;
    }
}
