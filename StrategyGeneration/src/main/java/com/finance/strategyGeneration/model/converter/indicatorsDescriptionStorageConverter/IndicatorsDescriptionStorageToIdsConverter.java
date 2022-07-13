package com.finance.strategyGeneration.model.converter.indicatorsDescriptionStorageConverter;

import com.finance.strategyGeneration.model.storage.IndicatorsDescriptionStorage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class IndicatorsDescriptionStorageToIdsConverter implements Converter<IndicatorsDescriptionStorage, String[]> {


    @Override
    public String[] convert(IndicatorsDescriptionStorage indicatorsDescriptionStorage) {
        return indicatorsDescriptionStorage.receiveStringIds().toArray(String[]::new);
    }
}
