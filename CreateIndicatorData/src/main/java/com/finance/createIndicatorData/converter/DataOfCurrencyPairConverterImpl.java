package com.finance.createIndicatorData.converter;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.Candle;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfCurrencyPairConverterImpl implements DataOfCurrencyPairConverter {

    CandleConverter candleConverter;

    @Override
    public DataOfCurrencyPair convertToDataOfCurrencyPair(List<Candle> candles) {
        Candle firstCandle = candles.get(0);
        return DataOfCurrencyPair.builder()
                .currencyPair(firstCandle.getTicker())
                .timeFrame(TimeFrame.getTimeFrameByPer(firstCandle.getPer()))
                .openingPrices(candleConverter.convertToIntOpenPrice(candles))
                .closingPrices(candleConverter.convertToIntClosePrice(candles))
                .highPrices(candleConverter.convertToIntHighPrice(candles))
                .lowPrices(candleConverter.convertToIntLowPrice(candles))
                .build();
    }
}
