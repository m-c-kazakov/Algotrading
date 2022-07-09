package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.service.CandlesInformationService;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators.GeneratorRandomParametersOfIndicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneratorOfRandomIndicatorsImpl implements GeneratorOfRandomIndicators {

    GeneratorRandomParametersOfIndicator randomParametersByIndicatorType;
    CandlesInformationService candlesInformationService;
    InformationOfIndicatorService informationOfIndicatorService;

    @Override
    public Indicator getRandomIndicator(CurrencyPair currencyPair) {

        IndicatorType indicatorType = IndicatorType.getRandomIndicatorType();

        TimeFrame timeFrame = TimeFrame.getRandomTimeFrame();

        Map<String, String> parameters = randomParametersByIndicatorType.getRandomParametersByIndicatorType(
                indicatorType);

        CandlesInformation candlesInformation = candlesInformationService.save(timeFrame, currencyPair);
        

        return informationOfIndicatorService.save(indicatorType, candlesInformation, parameters);
    }
}
