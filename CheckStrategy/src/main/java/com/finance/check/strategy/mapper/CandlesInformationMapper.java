package com.finance.check.strategy.mapper;

import com.finance.check.strategy.dto.CandlesInformationDto;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CandlesInformationMapper {

    CandlesInformation mapTo(CandlesInformationDto candlesInformationDto);
}
