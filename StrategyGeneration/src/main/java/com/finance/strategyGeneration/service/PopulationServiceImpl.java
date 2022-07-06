package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationServiceImpl implements PopulationService {


//    StrategyInformationRepository repository;

    @Override
    public List<DescriptionOfStrategy> findTheBestIndividual(int numberOfIndividuals) {


        Sort by = Sort.by(Sort.Direction.DESC, "score").ascending();
//        repository.findAll(by);

        return List.of();
    }
}
