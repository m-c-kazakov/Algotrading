package com.finance.strategyGeneration.service.mapper;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StrategyInformationMapper {


    @Mapping(target = "id", ignore = true)
    StatisticsOfStrategy mapTo(StatisticsDataOfStrategy statisticsDataOfStrategy);

    @Mapping(target = "hashCode", ignore = true)
    @Mapping(target = "statisticsOfStrategyId", ignore = true)
    @Mapping(target = "descriptionToOpenADeal", source = "descriptionToOpenADeal", qualifiedByName = "indicatorToId")
    @Mapping(target = "descriptionToCloseADeal", source = "descriptionToCloseADeal", qualifiedByName = "indicatorToId")
    @Mapping(target = "informationOfCandlesId", source = "candlesInformation", qualifiedByName = "candlesInformationToId")
    SpecificationOfStrategy mapTo(DescriptionOfStrategy descriptionOfStrategy);

    @Mapping(target = "dataOfCandles", ignore = true)
    @Mapping(target = "decisionToOpenADeal", ignore = true)
    @Mapping(target = "decisionToCloseADeal", ignore = true)
    @Mapping(target = "descriptionToOpenADeal", source = "descriptionToOpenADeal", qualifiedByName = "idToIndicator")
    @Mapping(target = "descriptionToCloseADeal", source = "descriptionToCloseADeal", qualifiedByName = "idToIndicator")
    @Mapping(target = "candlesInformation", source = "informationOfCandlesId", qualifiedByName = "idToCandlesInformation")
    DescriptionOfStrategy mapTo(SpecificationOfStrategy specificationOfStrategy);

    @Named("indicatorToId")
    static Long indicatorToId(Indicator indicator) {
        return indicator.getId();
    }

    @Named("idToIndicator")
    static Indicator idToIndicator(Long id) {
        return Indicator.builder().id(id).build();
    }

    @Named("idToCandlesInformation")
    static CandlesInformation idToCandlesInformation(Long id) {
        return CandlesInformation.builder().id(id).build();
    }

    @Named("candlesInformationToId")
    static Long candlesInformationToId(CandlesInformation candlesInformation) {
        return candlesInformation.getId();
    }


}
