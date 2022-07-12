package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.DataSender;
import com.finance.strategyGeneration.service.broker.StringValue;
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
    DataSender kafkaSender;
    SpecificationOfStrategyService specificationOfStrategyService;

    @Override
    @Scheduled(fixedDelay = 30000)
    public void execute() {

        // TODO доработать условие запуска стратегий

        Integer numberOfUntestedStrategies = specificationOfStrategyService.findTheNumberOfUntestedStrategies();
        log.info("Количество не проверенных стратегий {}", numberOfUntestedStrategies);

        if (numberOfUntestedStrategies < frontierForCreatingNewStrategies) {
            log.info("START: Запуск генетического алгоритма.");
            List<StringValue> values = geneticAlgorithm
                    .execute()
                    .stream()
                    .map(SpecificationOfStrategy::getId)
                    .map(id -> new StringValue(id, String.valueOf(id)))
                    .toList();


            log.info("END: Завершение генетического алгоритма. Количество созданных стратегий={}", values.size());


            kafkaSender.dataHandler(values);
        } else {
            log.info("Количество не проверенных стратегий больше {}. Новые стратегии не будут созданы.", frontierForCreatingNewStrategies);
        }
    }
}
