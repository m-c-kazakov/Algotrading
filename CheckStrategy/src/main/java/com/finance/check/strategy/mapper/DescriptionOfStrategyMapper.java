package com.finance.check.strategy.mapper;

import com.finance.check.strategy.dto.DescriptionOfStrategyDto;
import com.finance.dataHolder.DescriptionOfStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {IndicatorMapper.class, CandlesInformationMapper.class})
public interface DescriptionOfStrategyMapper {


    @Mapping(target = "dataOfCandles", ignore = true)
    @Mapping(target = "candlesInformation", source = "informationOfCandles")
    @Mapping(target = "decisionToOpenADeal", ignore = true)
    @Mapping(target = "decisionToCloseADeal", ignore = true)
    DescriptionOfStrategy mapTo(DescriptionOfStrategyDto descriptionOfStrategyDto);
}
