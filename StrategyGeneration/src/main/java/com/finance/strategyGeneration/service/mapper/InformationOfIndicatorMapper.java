package com.finance.strategyGeneration.service.mapper;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.model.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.service.CandlesInformationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class InformationOfIndicatorMapper {

    @Autowired
    CandlesInformationService candlesInformationService;

    @Mapping(target = "parameters", source = "parameters", qualifiedByName = "parametersObjectToMap")
    @Mapping(target = "candlesInformation", source = "informationOfCandlesId", qualifiedByName = "createCandlesInformation")
    public abstract Indicator mapTo(InformationOfIndicator informationOfIndicator);

    @Mapping(target = "parameters", source = "parameters", qualifiedByName = "mapToParametersObject")
    @Mapping(target = "hashCode", source = "indicator", qualifiedByName = "hashCodeGenerator")
    @Mapping(target = "informationOfCandlesId", source = "indicator", qualifiedByName = "getInformationOfCandlesId")
    public abstract InformationOfIndicator mapTo(Indicator indicator);

    @Named("parametersObjectToMap")
    static Map<String, String> parametersObjectToMap(IndicatorParametersConfigurationStorage parameters) {
        return parameters.getParameters();
    }

    @Named("mapToParametersObject")
    static IndicatorParametersConfigurationStorage mapToParametersObject(Map<String, String> parameters) {
        return new IndicatorParametersConfigurationStorage(parameters);
    }

    @Named("hashCodeGenerator")
    static long mapToParametersObject(Indicator indicator) {
        return indicator.hashCode();
    }

    @Named("getInformationOfCandlesId")
    static long getInformationOfCandlesId(Indicator indicator) {
        return indicator.getCandlesInformation().getId();
    }

    @Named("createCandlesInformation")
    CandlesInformation createCandlesInformation(long informationOfCandlesId) {
        return candlesInformationService.findById(informationOfCandlesId);
    }
}
