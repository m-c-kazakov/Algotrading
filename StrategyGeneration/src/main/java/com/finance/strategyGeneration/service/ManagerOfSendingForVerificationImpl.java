package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;
import com.finance.strategyGeneration.mapper.SpecificationOfStrategyMapper;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.producer.DataProducer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation.PopulationSelection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;


@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManagerOfSendingForVerificationImpl implements ManagerOfSendingForVerification {

    SpecificationOfStrategyMapper mapper;
    SpecificationOfStrategyService specificationOfStrategyService;
    Executor executor;
    DataProducer dataProducer;
    PopulationSelection populationSelection;

    @Override
    public void execute(SpecificationOfStrategy specificationOfStrategy) {

        try {

            Boolean isValidStrategy = populationSelection.isValidStrategy(specificationOfStrategy);
            if (isValidStrategy && specificationOfStrategyService
                    .findByHashCode(specificationOfStrategy.getHashCode())
                    .isEmpty()) {
                SpecificationOfStrategy entity = specificationOfStrategyService.save(specificationOfStrategy);
                SpecificationOfStrategyDto specificationOfStrategyDto = mapper.mapTo(entity);
                dataProducer.dataHandler(specificationOfStrategyDto);
                log.debug("Стратегия сохранена и отправлена в Kafka");
            } else {
                log.info("Стратегия уже существует либо не прошла проверку");
            }

        } catch (Exception e) {
            log.error("Ошибка при сохранении стратегий. ErrorMessage={}", e.getCause().toString());
            log.debug("Ошибка при сохранении стратегий={}", e.getMessage());
        }

    }

    @Override
    public void execute(List<SpecificationOfStrategy> specificationOfStrategies) {
        log.info("Создание асинхронных запросов на сохранение стратегий в БД и отправку в Kafka");

        specificationOfStrategies
                .forEach(specificationOfStrategy -> CompletableFuture.runAsync(
                        () -> this.execute(specificationOfStrategy), executor));

    }
}
