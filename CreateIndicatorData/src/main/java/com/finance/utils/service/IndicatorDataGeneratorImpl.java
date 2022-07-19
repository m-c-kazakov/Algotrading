package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.model.DataOfIndicator;
import com.finance.utils.repository.DataOfIndicatorRepository;
import com.finance.utils.service.converterToDataOfIndicator.ConverterToDataOfIndicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorDataGeneratorImpl implements IndicatorDataGenerator {

    Map<String, ConverterToDataOfIndicator> converterToDataOfIndicatorMap;

    DataOfIndicatorRepository dataOfIndicatorRepository;

    @Override
    public DataOfIndicator generate(Indicator indicator, TypeOfDeal typeOfDeal, DataOfCurrencyPair dataOfCurrencyPair) {
        return dataOfIndicatorRepository.getDataOfIndicatorByUniqueIdentifier(indicator.getUniqueIdentifier())
                .orElseGet(() -> {
                    ConverterToDataOfIndicator converterToDataOfIndicator = converterToDataOfIndicatorMap.get(
                            indicator.getIndicatorType().name());
                    DataOfIndicator dataOfIndicator =
                            converterToDataOfIndicator.convert(indicator, typeOfDeal, dataOfCurrencyPair);
                    dataOfIndicatorRepository.save(dataOfIndicator);
                    return dataOfIndicator;
                });


    }
}
