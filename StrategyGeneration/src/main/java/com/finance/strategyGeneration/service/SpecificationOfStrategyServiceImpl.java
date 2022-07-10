package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationOfStrategyServiceImpl implements SpecificationOfStrategyService {

    SpecificationOfStrategyRepository repository;
    InformationOfIndicatorService informationOfIndicatorService;

    @Override
    public List<SpecificationOfStrategy> saveAll(List<SpecificationOfStrategy> populationAfterSelection) {
        Map<String, SpecificationOfStrategy> duplicate = new HashMap<>();
        List<SpecificationOfStrategy> normal = new ArrayList<>();
        for (SpecificationOfStrategy specificationOfStrategy : populationAfterSelection) {
            try {
                normal.add(repository.save(specificationOfStrategy));
            } catch (Exception exception) {
                duplicate.put(String.valueOf(specificationOfStrategy.getHashCode()), specificationOfStrategy);
            }
        }

        System.out.println("asfd");
        return normal;
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
                            .withDescriptionToOpenADealIndicators(openADealIndicators)
                            .withDescriptionToCloseADealIndicators(closeADealIndicators);
                }).toList();
    }
}
