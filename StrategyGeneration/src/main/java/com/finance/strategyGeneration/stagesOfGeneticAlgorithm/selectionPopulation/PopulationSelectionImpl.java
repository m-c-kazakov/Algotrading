package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
