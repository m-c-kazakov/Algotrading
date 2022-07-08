package com.finance.strategyGeneration.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
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
public class SumOfDealConfigurationStorageToJsonConverter implements Converter<ConfigurationStorage<SumOfDealConfigurationKey>, PGobject> {

    ObjectMapper objectMapper;


    @Override
    @SneakyThrows
    public PGobject convert(ConfigurationStorage<SumOfDealConfigurationKey> source) {
        PGobject pGobject = new PGobject();
        pGobject.setType("jsonb");
        pGobject.setValue(objectMapper.writeValueAsString(source.getConfigurationData()));
        return pGobject;
    }
}
