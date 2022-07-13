package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.DataProducer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SchedulingServiceImpl implements SchedulingService {

    @NonFinal
    @Value("${app.populationCreation.frontierForCreatingNewStrategies}")
    Integer frontierForCreatingNewStrategies;
    GeneticAlgorithm geneticAlgorithm;
    DataProducer kafkaSender;
    SpecificationOfStrategyService specificationOfStrategyService;

    @Override
    @Scheduled(fixedDelay = 30000)
    public void execute() {

        Integer numberOfUntestedStrategies = specificationOfStrategyService.findTheNumberOfUntestedStrategies();
        log.info("Количество не проверенных стратегий {}", numberOfUntestedStrategies);

        if (numberOfUntestedStrategies < frontierForCreatingNewStrategies) {
            log.info("START: Запуск генетического алгоритма.");
            List<SpecificationOfStrategy> values = geneticAlgorithm.execute();
            log.info("END: Завершение генетического алгоритма. Количество созданных стратегий={}", values.size());

            kafkaSender.dataHandler(values);
        } else {
            log.info("Количество не проверенных стратегий больше {}. Новые стратегии не будут созданы.", frontierForCreatingNewStrategies);
        }
    }
}
