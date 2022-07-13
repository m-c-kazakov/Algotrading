package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.storage.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.storage.InformationOfCandlesStorage;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder(toBuilder = true)
@With
@RequiredArgsConstructor
@Table("information_of_indicator")
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfIndicator {

    @Id
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Long id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    String hashCode;
    IndicatorType indicatorType;
    InformationOfCandlesStorage informationOfCandles;
    IndicatorParametersConfigurationStorage parameters;


    public String receiveStringId() {
        return String.valueOf(id);
    }

    public TimeFrame receiveTimeFrame() {
        return informationOfCandles.receiveTimeFrame();
    }

    public CurrencyPair receiveCurrencyPair() {
        return informationOfCandles.receiveCurrencyPair();
    }


    public Long receiveInformationOfCandlesId() {
        return informationOfCandles.receiveId();
    }

    public Integer receiveIndicatorTypeHashCode() {
        return this.indicatorType.hashCode();
    }

    public Integer receiveInformationOfCandlesHashCode() {
        return this.informationOfCandles.hashCode();
    }

    public Integer receiveParametersHashCode() {
        return this.parameters.hashCode();
    }
}
