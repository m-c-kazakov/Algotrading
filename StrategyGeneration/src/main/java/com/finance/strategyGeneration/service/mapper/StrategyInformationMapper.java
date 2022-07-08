package com.finance.strategyGeneration.service.mapper;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.model.ConfigurationStorage;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.Map;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StrategyInformationMapper {

    @Mapping(target = "id", ignore = true)
    StatisticsOfStrategy mapTo(StatisticsDataOfStrategy statisticsDataOfStrategy);

    @Mapping(target = "hashCode", ignore = true)
    @Mapping(target = "statisticsOfStrategyId", ignore = true)
    @Mapping(target = "descriptionToOpenADeal", source = "descriptionToOpenADeal", qualifiedByName = "indicatorToId")
    @Mapping(target = "descriptionToCloseADeal", source = "descriptionToCloseADeal", qualifiedByName = "indicatorToId")
    @Mapping(target = "informationOfCandlesId", source = "candlesInformation", qualifiedByName = "candlesInformationToId")
    @Mapping(target = "sumOfDealConfigurationData", source = "sumOfDealConfigurationData", qualifiedByName = "sumOfDealMapConverter")
    @Mapping(target = "stopLossConfigurationData", source = "stopLossConfigurationData", qualifiedByName = "stopLossMapConverter")
    @Mapping(target = "trailingStopConfigurationData", source = "trailingStopConfigurationData", qualifiedByName = "trailingStopMapConverter")
    @Mapping(target = "takeProfitConfigurationData", source = "takeProfitConfigurationData", qualifiedByName = "takeProfitMapConverter")
    SpecificationOfStrategy mapTo(DescriptionOfStrategy descriptionOfStrategy);

    @Mapping(target = "dataOfCandles", ignore = true)
    @Mapping(target = "decisionToOpenADeal", ignore = true)
    @Mapping(target = "decisionToCloseADeal", ignore = true)
    @Mapping(target = "descriptionToOpenADeal", source = "descriptionToOpenADeal", qualifiedByName = "idToIndicator")
    @Mapping(target = "descriptionToCloseADeal", source = "descriptionToCloseADeal", qualifiedByName = "idToIndicator")
    @Mapping(target = "candlesInformation", source = "informationOfCandlesId", qualifiedByName = "idToCandlesInformation")
    @Mapping(target = "sumOfDealConfigurationData", source = "sumOfDealConfigurationData", qualifiedByName = "sumOfDealConfigurationDataConverter")
    @Mapping(target = "stopLossConfigurationData", source = "stopLossConfigurationData", qualifiedByName = "stopLossConfigurationDataConverter")
    @Mapping(target = "trailingStopConfigurationData", source = "trailingStopConfigurationData", qualifiedByName = "trailingStopConfigurationDataConverter")
    @Mapping(target = "takeProfitConfigurationData", source = "takeProfitConfigurationData", qualifiedByName = "takeProfitConfigurationDataConverter")
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

}
