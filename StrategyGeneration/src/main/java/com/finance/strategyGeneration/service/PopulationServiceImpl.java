package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
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
public class PopulationServiceImpl implements PopulationService {

    SpecificationOfStrategyRepository specificationOfStrategyRepository;
    StrategyInformationMapper mapper;
    InformationOfIndicatorService informationOfIndicatorService;

    @Override
    public List<DescriptionOfStrategy> findTheBestIndividual(int numberOfIndividuals) {

        return specificationOfStrategyRepository.findTheBestStrategy(100)
                .stream()
                .map(mapper::mapTo)
                .map(descriptionOfStrategy -> descriptionOfStrategy
                        .withDescriptionToOpenADeal(
                                this.addInformationOfIndicator(descriptionOfStrategy.getDescriptionToOpenADeal()))
                        .withDescriptionToCloseADeal(
                                this.addInformationOfIndicator(descriptionOfStrategy.getDescriptionToCloseADeal()))
                )
                .toList();
    }

    private List<Indicator> addInformationOfIndicator(List<Indicator> indicators) {
        return indicators
                .stream()
                .map(Indicator::getId)
                .map(informationOfIndicatorService::findById)
                .toList();
    }
}
