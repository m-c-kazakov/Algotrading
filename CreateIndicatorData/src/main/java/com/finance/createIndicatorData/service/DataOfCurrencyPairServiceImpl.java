package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.converter.DataOfCurrencyPairConverter;
import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.Candle;
import com.finance.createIndicatorData.repository.CandleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfCurrencyPairServiceImpl implements DataOfCurrencyPairService {

    CandleRepository candleRepository;
    DataOfCurrencyPairConverter dataOfCurrencyPairConverter;

    @Override
    public DataOfCurrencyPair getCurrencyPair(DataOfCurrencyPair dataOfCurrencyPair) {

        List<Candle> candles = candleRepository.findAllByTickerAndPer(dataOfCurrencyPair.getCurrencyPair(),
                dataOfCurrencyPair.getPer());

        return dataOfCurrencyPairConverter.convertToDataOfCurrencyPair(candles);
    }
}
