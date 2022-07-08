package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.service.mapper.StrategyInformationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class СheckingTheUniquenessOfStrategiesImpl implements СheckingTheUniquenessOfStrategies {

    SpecificationOfStrategyRepository specificationOfStrategyRepository;
    StrategyInformationMapper mapper;

    @Override
    public Set<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation) {


        Set<DescriptionOfStrategy> collect = populationAfterMutation.stream()
                .distinct()
                .map(mapper::mapTo)
                .filter(specificationOfStrategy -> !specificationOfStrategyRepository.existsByHashCode(
                        specificationOfStrategy.hashCode()))
                .map(mapper::mapTo)
                .collect(Collectors.toSet());

        return collect;
    }
}
