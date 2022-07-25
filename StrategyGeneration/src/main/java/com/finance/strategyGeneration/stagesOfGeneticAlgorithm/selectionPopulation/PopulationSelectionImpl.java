package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation;

import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationSelectionImpl implements PopulationSelection {

    СheckingTheUniquenessOfStrategies сheckingTheUniquenessOfStrategies;


    @Override
    public List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> populationAfterMutation) {

        try {
            log.info("START PopulationSelection populationBeforeSelection.size={}", populationAfterMutation.size());
            List<SpecificationOfStrategy> execute = сheckingTheUniquenessOfStrategies.execute(populationAfterMutation);
            log.info("END PopulationSelection populationAfterSelection.size={}", execute.size());
            return execute;
        } catch (Exception e) {
            log.error("ERROR PopulationSelection={}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Boolean isValidStrategy(SpecificationOfStrategy specificationOfStrategy) {
        return isInformationOfCandlesContainsMinimalTimeFrame(specificationOfStrategy);
    }

    private Boolean isInformationOfCandlesContainsMinimalTimeFrame(SpecificationOfStrategy specificationOfStrategy) {
        InformationOfCandles informationOfCandles = specificationOfStrategy.receiveInformationOfCandles();
        TimeFrame minimalTimeFrame = informationOfCandles.getTimeFrame();

        Optional<TimeFrame> result = Stream.of(specificationOfStrategy.receiveDescriptionToCloseADealIndicators(),
                        specificationOfStrategy.receiveDescriptionToCloseADealIndicators())
                .flatMap(List::stream)
                .map(InformationOfIndicator::receiveTimeFrame)
                .filter(timeFrame -> minimalTimeFrame.getPer() > timeFrame.getPer())
                .findFirst();


        result.ifPresent(timeFrame -> log.info(
                "InformationOfCandles содержит не минимальный time frame. minimalTimeFrame={}; indicatorTimeFrame={};",
                minimalTimeFrame, timeFrame));
        return result.isEmpty();
    }
}
