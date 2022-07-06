package com.finance.strategyGeneration.service;

import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import com.finance.strategyGeneration.service.mapper.StrategyInformationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsDataOfStrategyServiceImpl implements StatisticsDataOfStrategyService {

    StrategyInformationMapper strategyDescriptionMapper;
//    StrategyStatisticsInformationRepository strategyStatisticsInformationRepository;

    @Override
    public void save(StatisticsDataOfStrategy statisticsDataOfStrategy) {

        StatisticsOfStrategy statisticsOfStrategy = strategyDescriptionMapper.mapTo(
                statisticsDataOfStrategy);

//        strategyStatisticsInformationRepository.save(strategyStatisticsInformation);

        // TODO Получить с минимальное значение прибыли от 10% стратегий

        // TODO Если текущая стратегия Выше минимального значения, то запусти генерацию новых стратегий



    }
}
