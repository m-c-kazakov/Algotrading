package com.finance.strategyDescriptionParameters.indicators;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

@With
@Value
@Builder(toBuilder = true)
@Jacksonized
public class Indicator {

    @NonNull
    IndicatorType indicatorType;
    @NonNull
    CandlesInformation candlesInformation;
    @NonNull
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
        return this.toBuilder().build();
    }

    public TimeFrame getTimeFrame() {
        return this.candlesInformation.getTimeFrame();
    }

    public CurrencyPair getCurrencyPair() {
        return this.candlesInformation.getCurrencyPair();
    }

    public Indicator withTimeFrame(TimeFrame timeFrame) {
        return this.withCandlesInformation(this.candlesInformation.withTimeFrame(timeFrame));
    }
}
