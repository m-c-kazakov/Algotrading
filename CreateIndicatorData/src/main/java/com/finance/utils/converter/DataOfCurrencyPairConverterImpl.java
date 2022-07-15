package com.finance.utils.converter;

import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.model.Candle;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfCurrencyPairConverterImpl implements DataOfCurrencyPairConverter {

    CandleConverter candleConverter;

    @Override
    public DataOfCurrencyPair convertToDataOfCurrencyPair(List<Candle> candles) {
        Assert.notEmpty(candles, "candles не может быть пустой.");
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
