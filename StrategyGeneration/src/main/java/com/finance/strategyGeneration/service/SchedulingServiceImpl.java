package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.broker.DataSender;
import com.finance.strategyGeneration.service.broker.StringValue;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SchedulingServiceImpl implements SchedulingService {

    GeneticAlgorithm geneticAlgorithm;
    DataSender kafkaSender;

    @Override
//    @Scheduled(fixedDelay = 30000)
    public void execute() {

        // TODO доработать условие запуска стратегий

        log.info("START: Запуск генетического алгоритма.");
        List<StringValue> values = geneticAlgorithm
                .execute()
                .stream()
                .map(SpecificationOfStrategy::getId)
                .map(id -> new StringValue(id, String.valueOf(id)))
                .toList();


        log.info("END: Завершение генетического алгоритма. Количество созданных стратегий={}", values.size());


        kafkaSender.dataHandler(values);


    }
}
