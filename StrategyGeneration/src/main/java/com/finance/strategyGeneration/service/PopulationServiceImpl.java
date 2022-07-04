package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationServiceImpl implements PopulationService {

    PopulationDao populationDao;

    @Override
    public List<DescriptionOfStrategy> findTheBestIndividual(int numberOfIndividuals) {
        return populationDao.findTheBestIndividual(numberOfIndividuals);
    }
}
