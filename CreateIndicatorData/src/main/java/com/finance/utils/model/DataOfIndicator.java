package com.finance.utils.model;


import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Value
@Builder
@Table("data_of_indicators")
public class DataOfIndicator {

    @Id
    Long id;
    @NonFinal
    String uniqueIdentifier;
    TypeOfDeal typeOfDeal;
    List<Integer> decisionByDeal;
    IndicatorType indicatorType;
    CurrencyPair currencyPair;
    TimeFrame timeFrame;

    public String getUniqueIdentifier() {
        this.uniqueIdentifier =
                String.join("_", typeOfDeal.name(), indicatorType.name(), currencyPair.name(), timeFrame.name());
        return this.uniqueIdentifier;
    }
}
