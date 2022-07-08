package com.finance.strategyGeneration.service.mapper;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyGeneration.model.InformationOfCandles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InformationOfCandlesMapper {

    @Mapping(target = "hashCode", source = "candlesInformation", qualifiedByName = "hashCodeGenerator")
    InformationOfCandles mapTo(CandlesInformation candlesInformation);
    CandlesInformation mapTo(InformationOfCandles informationOfCandles);

    @Named("hashCodeGenerator")
    static long hashCodeGenerator(CandlesInformation candlesInformation) {
        return candlesInformation.hashCode();
    }
}
