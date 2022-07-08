package com.finance.strategyGeneration.service.mapper;

import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.model.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.Map;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InformationOfIndicatorMapper {

    @Mapping(target = "parameters", source = "parameters", qualifiedByName = "parametersObjectToMap")
    Indicator mapTo(InformationOfIndicator informationOfIndicator);

    @Mapping(target = "parameters", source = "parameters", qualifiedByName = "mapToParametersObject")
    @Mapping(target = "hashCode", source = "indicator", qualifiedByName = "hashCodeGenerator")
    InformationOfIndicator mapTo(Indicator indicator);

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
}
