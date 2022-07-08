package com.finance.strategyGeneration.service;

import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.repository.StatisticsOfStrategyRepository;
import com.finance.strategyGeneration.service.mapper.StrategyInformationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsDataOfStrategyServiceImpl implements StatisticsDataOfStrategyService {

    StrategyInformationMapper strategyDescriptionMapper;
    StatisticsOfStrategyRepository statisticsOfStrategyRepository;
    SpecificationOfStrategyRepository specificationOfStrategyRepository;

    @Override
    public void save(StatisticsDataOfStrategy statisticsDataOfStrategy) {

        StatisticsOfStrategy statisticsOfStrategy = strategyDescriptionMapper.mapTo(
                statisticsDataOfStrategy);

        StatisticsOfStrategy statisticsOfStrategyWithId = statisticsOfStrategyRepository.save(statisticsOfStrategy);

        specificationOfStrategyRepository.updateStatisticsOfStrategyId(
                statisticsOfStrategyWithId.getSpecificationOfStrategyId(), statisticsOfStrategyWithId.getId());
    }
}
