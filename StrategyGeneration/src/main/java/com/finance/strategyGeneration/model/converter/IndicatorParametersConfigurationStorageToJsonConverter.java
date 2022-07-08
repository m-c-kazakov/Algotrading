package com.finance.strategyGeneration.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.model.IndicatorParametersConfigurationStorage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;


@WritingConverter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorParametersConfigurationStorageToJsonConverter implements Converter<IndicatorParametersConfigurationStorage, String> {

    ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String convert(IndicatorParametersConfigurationStorage source) {
        return objectMapper.writeValueAsString(source.getParameters());
    }
}
