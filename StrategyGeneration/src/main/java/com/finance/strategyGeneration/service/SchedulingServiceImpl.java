package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.service.broker.consumer.DataConsumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SchedulingServiceImpl implements SchedulingService {


    GeneticAlgorithmManager geneticAlgorithmManager;
    DataConsumer dataConsumer;
    @NonFinal
    @Value("#{new Integer('${app.kafka.consumer.max_poll_records_config}')/2}")
    Integer frontierForCreatingNewStrategies;

    @Override
    @Scheduled(fixedDelayString = "#{new Integer('${app.kafka.consumer.max_poll_interval_ms_config}')*75/100}")
    public void execute() {

        Integer batchCount = dataConsumer.poll();
        log.info("Количество стратегий в пачке {}. Для создания новых стратегий должно быть меньше {} ", batchCount,
                frontierForCreatingNewStrategies);

        if (batchCount < frontierForCreatingNewStrategies) {

            geneticAlgorithmManager.execute();

        } else {
            log.info("Количество не проверенных стратегий больше {}. Новые стратегии не будут созданы.",
                    frontierForCreatingNewStrategies);
        }
    }
}
