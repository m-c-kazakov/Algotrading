package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.service.mapper.StrategyInformationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationSelectionImpl implements PopulationSelection {

    СheckingTheUniquenessOfStrategies сheckingTheUniquenessOfStrategies;

    SpecificationOfStrategyRepository specificationOfStrategyRepository;
    StrategyInformationMapper mapper;



    @Override
    public List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation) {

        return сheckingTheUniquenessOfStrategies.execute(populationAfterMutation)
                .stream()
                .map(mapper::mapTo)
                .filter(specificationOfStrategy -> !specificationOfStrategyRepository.existsByHashCode(
                        specificationOfStrategy.hashCode()))
                .map(mapper::mapTo)
                .toList();
    }
}
