package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Transactional
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationOfStrategyServiceImpl implements SpecificationOfStrategyService {

    SpecificationOfStrategyRepository repository;
    InformationOfIndicatorService informationOfIndicatorService;
    @Autowired
    @NonFinal
    SpecificationOfStrategyService self;

    @Transactional
    @Override
    public SpecificationOfStrategy save(SpecificationOfStrategy specificationOfStrategy) {

        return repository.save(specificationOfStrategy);
    }

    @Override
    public List<SpecificationOfStrategy> saveAll(List<SpecificationOfStrategy> populationAfterSelection) {
        // TODO сделать batch insert
        return Lists.newArrayList(repository.saveAll(populationAfterSelection));
    }

    @Override
    public List<SpecificationOfStrategy> findTheBestIndividual(int numberOfIndividuals) {

        List<SpecificationOfStrategy> theBestStrategy = repository.findTheBestStrategy(100);
        if (theBestStrategy.isEmpty()) {
            return List.of();
        }

        List<String> indicatorsId =
                theBestStrategy.stream()
                        .flatMap(specificationOfStrategy -> Stream.of(
                                specificationOfStrategy.getDescriptionToOpenADealStringIds(),
                                specificationOfStrategy.getDescriptionToCloseADealStringIds()))
                        .flatMap(List::stream)
                        .toList();


        Map<String, InformationOfIndicator> informationOfIndicatorMap = informationOfIndicatorService
                .findAllById(indicatorsId)
                .stream()
                .collect(Collectors.toMap(InformationOfIndicator::getStringId,
                        informationOfIndicator -> informationOfIndicator));


        return theBestStrategy.stream()
                .map(specificationOfStrategy -> {
                    List<InformationOfIndicator> openADealIndicators = specificationOfStrategy
                            .getDescriptionToOpenADealStringIds()
                            .stream()
                            .map(informationOfIndicatorMap::get)
                            .toList();

                    List<InformationOfIndicator> closeADealIndicators = specificationOfStrategy
                            .getDescriptionToCloseADealIndicators()
                            .stream()
                            .map(informationOfIndicatorMap::get)
                            .toList();

                    return specificationOfStrategy
                            .withId(null)
                            .withDescriptionToOpenADealIndicators(openADealIndicators)
                            .withDescriptionToCloseADealIndicators(closeADealIndicators);
                }).toList();
    }

    @Override
    public Integer findTheNumberOfUntestedStrategies() {
        return repository.findTheNumberOfUntestedStrategies();
    }
}
