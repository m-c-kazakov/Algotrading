package com.finance.strategyGeneration.service;

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
public class PopulationServiceImpl implements PopulationService {

    SpecificationOfStrategyRepository specificationOfStrategyRepository;
    StrategyInformationMapper mapper;

    @Override
    public List<DescriptionOfStrategy> findTheBestIndividual(int numberOfIndividuals) {

        return specificationOfStrategyRepository.findTheBestStrategy(100).stream()
                .map(mapper::mapTo).toList();
    }
}
