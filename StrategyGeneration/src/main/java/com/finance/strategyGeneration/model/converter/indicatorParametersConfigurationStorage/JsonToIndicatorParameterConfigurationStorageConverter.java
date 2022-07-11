package com.finance.strategyGeneration.model.converter.indicatorParametersConfigurationStorage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.model.storage.IndicatorParametersConfigurationStorage;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.HashMap;
import java.util.Map;


@ReadingConverter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JsonToIndicatorParameterConfigurationStorageConverter implements Converter<PGobject, IndicatorParametersConfigurationStorage> {

    ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public IndicatorParametersConfigurationStorage convert(@NonNull PGobject source) {
        String json = source.getValue();
        Map<String, String> map = objectMapper.readValue(json, HashMap.class);
        return new IndicatorParametersConfigurationStorage(map);
    }
}
