package com.finance.strategyDescriptionParameters.indicators;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@With
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Indicator {

    IndicatorType indicatorType;
    CandlesInformation candlesInformation;
    Map<String, String> parameters;

    @Builder
    public Indicator(IndicatorType indicatorType, TimeFrame timeFrame, CurrencyPair currencyPair,
                     Map<String, String> parameters) {
        this.indicatorType = indicatorType;
        this.candlesInformation = CandlesInformation.builder().currencyPair(currencyPair).timeFrame(timeFrame).build();
        this.parameters = Collections.unmodifiableMap(parameters);
    }

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
        return new Indicator(indicatorType, candlesInformation, getParameters());
    }

    public TimeFrame getTimeFrame() {
        return this.candlesInformation.getTimeFrame();
    }

    public CurrencyPair getCurrencyPair() {
        return this.candlesInformation.getCurrencyPair();
    }

    public Indicator withTimeFrame(TimeFrame timeFrame) {
        return new Indicator(indicatorType, this.candlesInformation.withTimeFrame(timeFrame), getParameters());
    }
}
