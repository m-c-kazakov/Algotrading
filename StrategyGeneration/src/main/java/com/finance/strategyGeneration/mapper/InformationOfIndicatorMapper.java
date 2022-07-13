package com.finance.strategyGeneration.mapper;

import com.finance.strategyGeneration.dto.InformationOfIndicatorDto;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {InformationOfCandlesMapper.class})
public interface InformationOfIndicatorMapper {

    @Mapping(target = "parameters", source = "parameters.parameters" )
    @Mapping(target = "informationOfCandles", source = "informationOfCandles.informationOfCandles" )
    InformationOfIndicatorDto mapTo(InformationOfIndicator informationOfIndicator);
}
