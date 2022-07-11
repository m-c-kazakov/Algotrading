package com.finance.strategyGeneration.model.converter.informationOfCandlesStorageConverter;

import com.finance.strategyGeneration.model.creator.InformationOfCandlesStorageCreator;
import com.finance.strategyGeneration.model.storage.InformationOfCandlesStorage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class IdToInformationOfCandlesStorageConverter implements Converter<String, InformationOfCandlesStorage> {

    @Override
    public InformationOfCandlesStorage convert(String source) {
        return InformationOfCandlesStorageCreator.create(source);
    }
}
