package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.model.DataOfCurrencyPair;
import com.finance.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DealDecisionServiceImpl implements DealDecisionService {
    IndicatorDataGenerator indicatorDataGenerator;

    @Override
    public byte[] makeDecisionOnOpeningDeal(TimeFrame theSmallestTimeFrame, DescriptionToOpenADeal descriptionToOpenADeal,
                                            Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap) {


        // Отдать данные валютных пар на обработку индикаторам
        Indicator indicator = null;
        byte[] indicatorResult = indicatorDataGenerator.generate(indicator, dataOfCurrencyPairMap.get(indicator.candlesInformationToString()));
        // Привести данные индикатора к 1 тайм фрейму
        // свести результат работы индикаторов к 1 массиву
        return new byte[0];
    }
}
