package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.service.mapper.StrategyInformationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class СheckingTheUniquenessOfStrategiesImpl implements СheckingTheUniquenessOfStrategies {

    SpecificationOfStrategyRepository specificationOfStrategyRepository;
    StrategyInformationMapper mapper;

    @Override
    public List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation) {


        List<DescriptionOfStrategy> distinct = populationAfterMutation.stream()
                .distinct()
                .toList();

        List<SpecificationOfStrategy> specificationOfStrategies = distinct
                .stream()
                .map(mapper::mapTo).toList();

        List<SpecificationOfStrategy> specificationOfStrategies1 = specificationOfStrategies.stream()
                .filter(specificationOfStrategy -> !specificationOfStrategyRepository.existsByHashCode(
                        specificationOfStrategy.hashCode()))
                .toList();


        return specificationOfStrategies1.stream()
                .map(mapper::mapTo)
                .toList();
    }
}
