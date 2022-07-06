package com.finance.strategyGeneration.service.mapper;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StrategyInformationMapper {

    StrategyInformationMapper INSTANCE = Mappers.getMapper(StrategyInformationMapper.class );

    @Mapping(target = "id", ignore = true)
    StatisticsOfStrategy mapTo(StatisticsDataOfStrategy statisticsDataOfStrategy);

    @Mapping(target = "hashCode", ignore = true)
    @Mapping(target = "statisticsOfStrategyId", ignore = true)
    SpecificationOfStrategy mapTo(DescriptionOfStrategy descriptionOfStrategy);

    @Mapping(target = "dataOfCandles", ignore = true)
    @Mapping(target = "decisionToOpenADeal", ignore = true)
    @Mapping(target = "decisionToCloseADeal", ignore = true)
    DescriptionOfStrategy mapTo(SpecificationOfStrategy specificationOfStrategy);
}
