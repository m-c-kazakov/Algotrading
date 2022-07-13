package com.finance.strategyGeneration.mapper;


import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {InformationOfIndicatorMapper.class})
public interface SpecificationOfStrategyMapper {

    @Mapping(target = "sumOfDealConfigurationData", source = "sumOfDealConfigurationData.configurationData")
    @Mapping(target = "stopLossConfigurationData", source = "stopLossConfigurationData.configurationData")
    @Mapping(target = "trailingStopConfigurationData", source = "trailingStopConfigurationData.configurationData")
    @Mapping(target = "takeProfitConfigurationData", source = "takeProfitConfigurationData.configurationData")
    @Mapping(target = "informationOfCandles", source = "informationOfCandles.informationOfCandles")
    @Mapping(target = "descriptionToOpenADeal", source = "descriptionToOpenADeal.informationOfIndicators")
    @Mapping(target = "descriptionToCloseADeal", source = "descriptionToCloseADeal.informationOfIndicators")
    SpecificationOfStrategyDto mapTo(SpecificationOfStrategy specificationOfStrategy);
}
