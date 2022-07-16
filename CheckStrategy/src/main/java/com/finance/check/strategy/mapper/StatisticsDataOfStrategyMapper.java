package com.finance.check.strategy.mapper;

import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.utils.StatisticsDataOfStrategyDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatisticsDataOfStrategyMapper {

    StatisticsDataOfStrategyDto mapTo(StatisticsDataOfStrategy statisticsDataOfStrategy);
}
