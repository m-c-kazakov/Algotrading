package com.finance.check.strategy.mapper;

import com.finance.check.strategy.dto.IndicatorDto;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CandlesInformationMapper.class})
public interface IndicatorMapper {

    @Mapping(source = "informationOfCandles", target = "candlesInformation")
    Indicator map(IndicatorDto indicatorDto);
}
