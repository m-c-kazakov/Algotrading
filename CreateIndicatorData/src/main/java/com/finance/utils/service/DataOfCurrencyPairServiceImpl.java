package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.utils.converter.DataOfCurrencyPairConverter;
import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.model.Candle;
import com.finance.utils.repository.CandleRepository;
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
    public DataOfCurrencyPair getCurrencyPair(CandlesInformation candlesInformation) {

        List<Candle> candles = candleRepository.findAllByTickerAndPer(candlesInformation.getCurrencyPair(),
                candlesInformation.getPer());

        return dataOfCurrencyPairConverter.convertToDataOfCurrencyPair(candles);
    }
}
