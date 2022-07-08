package com.finance.strategyDescriptionParameters.indicators;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;
import java.util.Map;

@With
@Value
@Builder
@Jacksonized
public class Indicator {

    @EqualsAndHashCode.Exclude
    Long id;
    IndicatorType indicatorType;
    CandlesInformation candlesInformation;
    Map<String, String> parameters;

    public Map<String, String> getParameters() {
        return new HashMap<>(parameters);
    }

    public String getValueFromParametersByKey(String key) {
        return parameters.get(key);
    }

    public String candlesInformationToString() {
        return this.candlesInformation.toString();
    }

    public Indicator copy() {
        return Indicator.builder().indicatorType(indicatorType)
                .candlesInformation(candlesInformation)
                .parameters(this.getParameters())
                .build();
    }

    public TimeFrame getTimeFrame() {
        return this.candlesInformation.getTimeFrame();
    }

    public CurrencyPair getCurrencyPair() {
        return this.candlesInformation.getCurrencyPair();
    }

    public Indicator withTimeFrame(TimeFrame timeFrame) {
        return Indicator.builder().indicatorType(indicatorType)
                .candlesInformation(CandlesInformation.builder().timeFrame(timeFrame).currencyPair(this.candlesInformation.getCurrencyPair()).build())
                .parameters(this.getParameters())
                .build();
    }
}
