package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;
import com.finance.strategyGeneration.mapper.SpecificationOfStrategyMapper;
import com.finance.strategyGeneration.service.broker.consumer.DataConsumer;
import com.finance.strategyGeneration.service.broker.producer.DataProducer;
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
    @Value("#{new Integer('${app.kafka.consumer.max_poll_records_config}')/2}")
    Integer frontierForCreatingNewStrategies;

    GeneticAlgorithm geneticAlgorithm;
    DataProducer dataProducer;
    DataConsumer dataConsumer;
    SpecificationOfStrategyMapper mapper;


    @Override
    @Scheduled(fixedDelayString = "#{new Integer('${app.kafka.consumer.max_poll_interval_ms_config}')*75/100}")
    public void execute() {

        Integer batchCount = dataConsumer.poll();
        log.info("Количество стратегий в пачке {}. Для создания новых стратегий должно быть меньше {} ", batchCount, frontierForCreatingNewStrategies);

        if (batchCount < frontierForCreatingNewStrategies) {
            log.info("START: Запуск генетического алгоритма.");
            List<SpecificationOfStrategyDto> specificationOfStrategyDtos =
                    geneticAlgorithm.execute().stream().map(mapper::mapTo).toList();
            log.info("END: Завершение генетического алгоритма. Количество созданных стратегий={}", specificationOfStrategyDtos.size());

            dataProducer.dataHandler(specificationOfStrategyDtos);
        } else {
            log.info("Количество не проверенных стратегий больше {}. Новые стратегии не будут созданы.", frontierForCreatingNewStrategies);
        }
    }
}
