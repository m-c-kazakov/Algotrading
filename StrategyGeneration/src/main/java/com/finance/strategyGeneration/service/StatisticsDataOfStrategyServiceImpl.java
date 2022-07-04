package com.finance.strategyGeneration.service;

import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyGeneration.model.StrategyStatisticsInformation;
import com.finance.strategyGeneration.repository.StrategyStatisticsInformationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsDataOfStrategyServiceImpl implements StatisticsDataOfStrategyService {

    StrategyStatisticsInformationMapper strategyStatisticsInformationMapper;
    StrategyStatisticsInformationRepository strategyStatisticsInformationRepository;

    @Override
    public void save(StatisticsDataOfStrategy statisticsDataOfStrategy) {

        StrategyStatisticsInformation strategyStatisticsInformation = strategyStatisticsInformationMapper.mapTo(
                statisticsDataOfStrategy);

//        strategyStatisticsInformationRepository.save(strategyStatisticsInformation);

        // TODO Получить с минимальное значение прибыли от 10% стратегий

        // TODO Если текущая стратегия Выше минимального значения, то запусти генерацию новых стратегий



    }
}
