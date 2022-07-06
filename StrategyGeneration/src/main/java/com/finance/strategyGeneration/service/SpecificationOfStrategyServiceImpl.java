package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
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
public class SpecificationOfStrategyServiceImpl implements SpecificationOfStrategyService {

    SpecificationOfStrategyRepository specificationOfStrategyRepository;
    StrategyInformationMapper mapper;

    @Override
    public void saveAll(List<DescriptionOfStrategy> populationAfterSelection) {

        List<SpecificationOfStrategy> specificationOfStrategies = populationAfterSelection.stream().map(mapper::mapTo)
                .toList();

        specificationOfStrategyRepository.saveAll(specificationOfStrategies);
    }
}
