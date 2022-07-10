package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
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
public class PopulationServiceImpl implements PopulationService {

    SpecificationOfStrategyRepository specificationOfStrategyRepository;

    @Override
    public List<SpecificationOfStrategy> findTheBestIndividual(int numberOfIndividuals) {

        return specificationOfStrategyRepository.findTheBestStrategy(100);
    }
}
