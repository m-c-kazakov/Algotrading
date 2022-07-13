package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.indicatorMutation;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.creator.IndicatorsDescriptionStorageCreator;
import com.finance.strategyGeneration.model.storage.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators.generatorRandomParametersByIndicatorType.GeneratorRandomParametersByIndicatorType;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
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

    InformationOfIndicatorService informationOfIndicatorService;


    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        List<InformationOfIndicator> indicators = new ArrayList<>(informationOfIndicatorService.findAllById(parentSpecificationOfStrategy.receiveDescriptionToOpenADealStringIds()));

        int bound = Math.max(indicators.size() / 2, 1);
        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current()
                .nextInt(bound), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current()
                    .nextInt(indicators.size());

            InformationOfIndicator indicatorForReplace = indicators.get(replacedIndex).toBuilder().build();

            IndicatorType indicatorType = indicatorForReplace.getIndicatorType();

            List<String> namesOfIndicatorParameters = indicatorType.getNamesOfIndicatorParameters();

            String keyOfIndicatorParameter = namesOfIndicatorParameters.get(ThreadLocalRandom.current()
                    .nextInt(namesOfIndicatorParameters.size()));

            Map<String, Supplier<String>> mapWithRandomMutationSuppliers = randomParameters
                    .get(indicatorType.name())
                    .getParametersSupplierMap();

            Supplier<String> supplierToMutationParameter = mapWithRandomMutationSuppliers.get(keyOfIndicatorParameter);

            Map<String, String> parametersOfIndicator = new HashMap<>(indicatorForReplace.getParameters().getParameters());
            parametersOfIndicator.put(keyOfIndicatorParameter, supplierToMutationParameter.get());
            indicatorForReplace.withParameters(new IndicatorParametersConfigurationStorage(parametersOfIndicator));

            informationOfIndicatorService.create(indicatorForReplace);


            indicators.set(replacedIndex, indicatorForReplace);
        }

        SpecificationOfStrategy SpecificationOfStrategyAfterMutation =
                parentSpecificationOfStrategy.withDescriptionToOpenADeal(IndicatorsDescriptionStorageCreator.create(indicators));

        return Stream.of(parentSpecificationOfStrategy, SpecificationOfStrategyAfterMutation);
    }

}
