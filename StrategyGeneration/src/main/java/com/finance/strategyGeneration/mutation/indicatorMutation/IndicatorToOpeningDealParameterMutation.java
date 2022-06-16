package com.finance.strategyGeneration.mutation.indicatorMutation;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.indicator.RandomIndicatorParametersGenerator;
import com.finance.strategyGeneration.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorToOpeningDealParameterMutation implements Mutation {
    Map<String, RandomIndicatorParametersGenerator> randomParameters;

    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {

        List<Indicator> indicators = parentDataOfStrategy.getIndicatorsDescriptionToOpenADeal();

        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current()
                .nextInt(indicators.size() / 2), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current()
                    .nextInt(indicators.size());

            Indicator indicatorForReplace = indicators.get(replacedIndex)
                    .copy();

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

        DataOfStrategy dataOfStrategyAfterMutation = parentDataOfStrategy.withDescriptionToOpenADeal(
                new DescriptionToOpenADeal(indicators));

        return Stream.of(parentDataOfStrategy, dataOfStrategyAfterMutation);
    }

}
