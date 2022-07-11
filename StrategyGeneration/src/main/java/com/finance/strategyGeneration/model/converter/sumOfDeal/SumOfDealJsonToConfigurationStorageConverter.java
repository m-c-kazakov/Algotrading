package com.finance.strategyGeneration.model.converter.sumOfDeal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
import com.finance.strategyGeneration.model.storage.ConfigurationStorage;
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
public class SumOfDealJsonToConfigurationStorageConverter implements Converter<PGobject, ConfigurationStorage<SumOfDealConfigurationKey>> {

    ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public ConfigurationStorage<SumOfDealConfigurationKey> convert(@NonNull PGobject source) {
        String json = source.getValue();
        Map<SumOfDealConfigurationKey, Object> map = objectMapper.readValue(json, HashMap.class);
        return new ConfigurationStorage<>(map);
    }
}
