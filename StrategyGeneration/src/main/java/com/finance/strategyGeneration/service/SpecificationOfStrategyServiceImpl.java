package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.service.mapper.StrategyInformationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationOfStrategyServiceImpl implements SpecificationOfStrategyService {

    SpecificationOfStrategyRepository specificationOfStrategyRepository;
    StrategyInformationMapper mapper;

    @Override
    public void saveAll(Set<DescriptionOfStrategy> populationAfterSelection) {

        List<SpecificationOfStrategy> specificationOfStrategies = populationAfterSelection
                .stream()
                .distinct()
                .map(mapper::mapTo)
                .toList();

        specificationOfStrategyRepository.saveAll(specificationOfStrategies);
    }

}
