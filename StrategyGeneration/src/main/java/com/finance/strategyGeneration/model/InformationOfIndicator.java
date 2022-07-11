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


    public String getStringId() {
        return String.valueOf(id);
    }

    public TimeFrame getTimeFrame() {
        return informationOfCandles.getTimeFrame();
    }

    public CurrencyPair getCurrencyPair() {
        return informationOfCandles.getCurrencyPair();
    }


    public Long getInformationOfCandlesId() {
        return informationOfCandles.getId();
    }

    public Integer getIndicatorTypeHashCode() {
        return this.indicatorType.hashCode();
    }

    public Integer getInformationOfCandlesHashCode() {
        return this.informationOfCandles.hashCode();
    }

    public Integer getParametersHashCode() {
        return this.parameters.hashCode();
    }
}
