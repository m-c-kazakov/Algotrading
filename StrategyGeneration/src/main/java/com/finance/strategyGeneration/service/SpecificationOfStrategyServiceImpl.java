package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @Override
    // TODO Возможна проблема. Т.к. на разные String можнт быть одинаковый hashCode, то в БД он может для разных стратегий возвращать одинаковые результаты
    @Cacheable(
            cacheNames = "findByHashCodeSpecificationOfStrategy",
            key = "#hashCode"
    )
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public Optional<SpecificationOfStrategy> findByHashCode(String hashCode) {
        return repository.findByHashCode(hashCode);
    }

    @Override
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public SpecificationOfStrategy save(SpecificationOfStrategy specificationOfStrategy) {
        return repository.save(specificationOfStrategy);
    }

    @Override
    public List<SpecificationOfStrategy> saveAll(List<SpecificationOfStrategy> populationAfterSelection) {
        try {
            // TODO сделать batch insert
            log.info("START SpecificationOfStrategyService");
            ArrayList<SpecificationOfStrategy> specificationOfStrategies =
                    Lists.newArrayList(repository.saveAll(populationAfterSelection));
            log.info("END SpecificationOfStrategyService");
            return specificationOfStrategies;
        } catch (Exception exception) {
            log.error("ERROR SpecificationOfStrategyService.saveAll={}", exception.getMessage(), exception);
            throw exception;
        }
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
                                specificationOfStrategy.receiveDescriptionToOpenADealStringIds(),
                                specificationOfStrategy.receiveDescriptionToCloseADealStringIds()))
                        .flatMap(List::stream)
                        .toList();


        Map<String, InformationOfIndicator> informationOfIndicatorMap = informationOfIndicatorService
                .findAllById(indicatorsId)
                .stream()
                .collect(Collectors.toMap(InformationOfIndicator::receiveStringId,
                        informationOfIndicator -> informationOfIndicator));


        return theBestStrategy.stream()
                .map(specificationOfStrategy -> {
                    List<InformationOfIndicator> openADealIndicators = specificationOfStrategy
                            .receiveDescriptionToOpenADealStringIds()
                            .stream()
                            .map(informationOfIndicatorMap::get)
                            .toList();

                    List<InformationOfIndicator> closeADealIndicators = specificationOfStrategy
                            .receiveDescriptionToCloseADealIndicators()
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
        return repository.findTheNumberOfUntestedStrategies().orElse(0);
    }
}
