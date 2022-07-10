package com.finance.strategyGeneration.model.converter.trailingStop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyGeneration.model.ConfigurationStorage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;


@WritingConverter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TrailingStopConfigurationStorageToJsonConverter implements Converter<ConfigurationStorage<TrailingStopConfigurationKey>, PGobject> {

    ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public PGobject convert(ConfigurationStorage<TrailingStopConfigurationKey> source) {
        PGobject pGobject = new PGobject();
        pGobject.setType("json");
        pGobject.setValue(objectMapper.writeValueAsString(source.getConfigurationData()));
        return pGobject;
    }
}
