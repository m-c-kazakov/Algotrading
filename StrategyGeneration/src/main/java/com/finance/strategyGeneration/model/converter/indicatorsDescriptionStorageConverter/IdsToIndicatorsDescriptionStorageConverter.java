package com.finance.strategyGeneration.model.converter.indicatorsDescriptionStorageConverter;


import com.finance.strategyGeneration.model.creator.IndicatorsDescriptionStorageCreator;
import com.finance.strategyGeneration.model.storage.IndicatorsDescriptionStorage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;

@ReadingConverter
public class IdsToIndicatorsDescriptionStorageConverter implements Converter<String[], IndicatorsDescriptionStorage> {

    @Override
    public IndicatorsDescriptionStorage convert(@NonNull String[] ids) {
        return IndicatorsDescriptionStorageCreator.create(ids);
    }
}
