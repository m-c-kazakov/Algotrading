package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;
import com.finance.strategyGeneration.mapper.SpecificationOfStrategyMapper;
import com.finance.strategyGeneration.service.broker.producer.DataProducer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Executor;


@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneticAlgorithmManagerImpl implements GeneticAlgorithmManager {

    GeneticAlgorithm geneticAlgorithm;
    SpecificationOfStrategyMapper mapper;
    Executor executor;
    DataProducer dataProducer;

    @Override
    public void execute() {
        log.info("Старт запуска потока по созданию стратегий");

        try {
            log.info("START: Запуск генетического алгоритма.");
            List<SpecificationOfStrategyDto> specificationOfStrategyDtos =
                    geneticAlgorithm.execute().stream().map(mapper::mapTo).toList();
            dataProducer.dataHandler(specificationOfStrategyDtos);
            log.info("END: Завершение генетического алгоритма. Количество созданных стратегий={}",
                    specificationOfStrategyDtos.size());
        } catch (Exception e) {
            log.error("Ошибка при создании стратегий.", e);
        }
//  Не видно логов с нового потока
//        executor.execute(() -> {
//
//        });

    }
}
