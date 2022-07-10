package com.finance.strategyGeneration.model.converter.informationOfCandlesStorageConverter;

import com.finance.strategyGeneration.model.InformationOfCandlesStorage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class InformationOfCandlesStorageToIdConverter implements Converter<InformationOfCandlesStorage, String> {

    @Override
    public String convert(InformationOfCandlesStorage source) {
        return source.getStringId();
    }
}
