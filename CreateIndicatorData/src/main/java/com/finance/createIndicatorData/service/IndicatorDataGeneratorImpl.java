package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.DataOfIndicator;
import com.finance.createIndicatorData.repository.DataOfIndicatorRepository;
import com.finance.createIndicatorData.service.converterToDataOfIndicator.ConverterToDataOfIndicator;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorDataGeneratorImpl implements IndicatorDataGenerator {

    Map<String, ConverterToDataOfIndicator> converterToDataOfIndicatorMap;

    DataOfIndicatorRepository dataOfIndicatorRepository;

    @Override
    public DataOfIndicator generate(Indicator indicator, TypeOfDeal typeOfDeal, DataOfCurrencyPair dataOfCurrencyPair) {
        return dataOfIndicatorRepository.getDataOfIndicatorByIndicatorTypeAndAndCurrencyPairAndTimeFrame(
                indicator.getIndicatorType().name(),
                dataOfCurrencyPair.getCurrencyPair().name(),
                dataOfCurrencyPair.getTimeFrame().name())
                .orElseGet(() -> {
                    ConverterToDataOfIndicator converterToDataOfIndicator = converterToDataOfIndicatorMap.get(
                            indicator.getIndicatorType().name());
                    DataOfIndicator dataOfIndicator = converterToDataOfIndicator.convert(indicator, typeOfDeal, dataOfCurrencyPair);
                    dataOfIndicatorRepository.save(dataOfIndicator);
                    return dataOfIndicator;
                });



    }
}
