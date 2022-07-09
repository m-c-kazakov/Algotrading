package com.finance.strategyGeneration.service.mapper;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.model.ConfigurationStorage;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import com.finance.strategyGeneration.service.CandlesInformationService;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class StrategyInformationMapper {

    // TODO УДалить лишние методы используя более правильные названия для a.b для того чтобы mapstruct понимал откуда брать
    @Autowired
    InformationOfIndicatorService informationOfIndicatorService;
    @Autowired
    CandlesInformationService candlesInformationService;

    @Mapping(target = "id", ignore = true)
    public abstract StatisticsOfStrategy mapTo(StatisticsDataOfStrategy statisticsDataOfStrategy);

    @Mapping(target = "hashCode", source = "descriptionOfStrategy", qualifiedByName = "getHashCode")
    @Mapping(target = "statisticsOfStrategyId", ignore = true)
    @Mapping(target = "sumOfDealConfigurationData", source = "sumOfDealConfigurationData", qualifiedByName = "sumOfDealMapConverter")
    @Mapping(target = "stopLossConfigurationData", source = "stopLossConfigurationData", qualifiedByName = "stopLossMapConverter")
    @Mapping(target = "trailingStopConfigurationData", source = "trailingStopConfigurationData", qualifiedByName = "trailingStopMapConverter")
    @Mapping(target = "takeProfitConfigurationData", source = "takeProfitConfigurationData", qualifiedByName = "takeProfitMapConverter")
    @Mapping(target = "informationOfCandlesId", source = "candlesInformation", qualifiedByName = "getInformationOfCandlesId")
    @Mapping(target = "descriptionToOpenADeal", source = "descriptionToOpenADeal", qualifiedByName = "indicatorToId")
    @Mapping(target = "descriptionToCloseADeal", source = "descriptionToCloseADeal", qualifiedByName = "indicatorToId")
    public abstract SpecificationOfStrategy mapTo(DescriptionOfStrategy descriptionOfStrategy);

    @Mapping(target = "dataOfCandles", ignore = true)
    @Mapping(target = "decisionToOpenADeal", ignore = true)
    @Mapping(target = "decisionToCloseADeal", ignore = true)
    @Mapping(target = "descriptionToOpenADeal", source = "descriptionToOpenADeal", qualifiedByName = "idToIndicatorDescriptionToOpenADeal")
    @Mapping(target = "descriptionToCloseADeal", source = "descriptionToCloseADeal", qualifiedByName = "idToIndicatorDescriptionToCloseADeal")
    @Mapping(target = "candlesInformation", source = "informationOfCandlesId", qualifiedByName = "idToCandlesInformation")
    @Mapping(target = "sumOfDealConfigurationData", source = "sumOfDealConfigurationData", qualifiedByName = "sumOfDealConfigurationDataConverter")
    @Mapping(target = "stopLossConfigurationData", source = "stopLossConfigurationData", qualifiedByName = "stopLossConfigurationDataConverter")
    @Mapping(target = "trailingStopConfigurationData", source = "trailingStopConfigurationData", qualifiedByName = "trailingStopConfigurationDataConverter")
    @Mapping(target = "takeProfitConfigurationData", source = "takeProfitConfigurationData", qualifiedByName = "takeProfitConfigurationDataConverter")
    public abstract DescriptionOfStrategy mapTo(SpecificationOfStrategy specificationOfStrategy);

    @Named("getHashCode")
    static int getHashCode(DescriptionOfStrategy descriptionOfStrategy) {
        return descriptionOfStrategy.hashCode();
    }

    @Named("indicatorToId")
    static Long indicatorToId(Indicator indicator) {
        return indicator.getId();
    }

    @Named("idToIndicatorDescriptionToOpenADeal")
    Indicator idToIndicatorDescriptionToOpenADeal(Long id) {
        return informationOfIndicatorService.findById(id);
    }

    @Named("idToIndicatorDescriptionToCloseADeal")
    Indicator idToIndicatorDescriptionToCloseADeal(Long id) {
        return informationOfIndicatorService.findById(id);
    }

    @Named("idToCandlesInformation")
    CandlesInformation idToCandlesInformation(Long id) {
        return candlesInformationService.findById(id);
    }

    @Named("sumOfDealConfigurationDataConverter")
    static Map<SumOfDealConfigurationKey, Object> sumOfDealConfigurationDataConverter(ConfigurationStorage<SumOfDealConfigurationKey> configuration) {
        return configuration.getConfigurationData();
    }
    @Named("stopLossConfigurationDataConverter")
    static Map<StopLossConfigurationKey, Object> stopLossConfigurationDataConverter(ConfigurationStorage<StopLossConfigurationKey> configuration) {
        return configuration.getConfigurationData();
    }

    @Named("trailingStopConfigurationDataConverter")
    static Map<TrailingStopConfigurationKey, Object> trailingStopConfigurationDataConverter(ConfigurationStorage<TrailingStopConfigurationKey> configuration) {
        return configuration.getConfigurationData();
    }

    @Named("takeProfitConfigurationDataConverter")
    static Map<TakeProfitConfigurationKey, Object> takeProfitConfigurationDataConverter(ConfigurationStorage<TakeProfitConfigurationKey> configuration) {
        return configuration.getConfigurationData();
    }

    @Named("sumOfDealMapConverter")
    static ConfigurationStorage<SumOfDealConfigurationKey> sumOfDealMapConverter(Map<SumOfDealConfigurationKey, Object> configuration) {
        return new ConfigurationStorage<>(configuration);
    }

    @Named("stopLossMapConverter")
    static ConfigurationStorage<StopLossConfigurationKey> stopLossMapConverter(Map<StopLossConfigurationKey, Object> configuration) {
        return new ConfigurationStorage<>(configuration);
    }

    @Named("trailingStopMapConverter")
    static ConfigurationStorage<TrailingStopConfigurationKey> trailingStopMapConverter(Map<TrailingStopConfigurationKey, Object> configuration) {
        return new ConfigurationStorage<>(configuration);
    }

    @Named("takeProfitMapConverter")
    static ConfigurationStorage<TakeProfitConfigurationKey> takeProfitMapConverter(Map<TakeProfitConfigurationKey, Object> configuration) {
        return new ConfigurationStorage<>(configuration);
    }

    @Named("getInformationOfCandlesId")
    static Long getInformationOfCandlesId(CandlesInformation candlesInformation) {
        return candlesInformation.getId();
    }

}
