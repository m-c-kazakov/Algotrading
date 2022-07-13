package com.finance.strategyGeneration.mapper;

import com.finance.strategyGeneration.dto.InformationOfCandlesDto;
import com.finance.strategyGeneration.model.InformationOfCandles;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InformationOfCandlesMapper {


    InformationOfCandlesDto mapTo(InformationOfCandles informationOfCandles);
}
