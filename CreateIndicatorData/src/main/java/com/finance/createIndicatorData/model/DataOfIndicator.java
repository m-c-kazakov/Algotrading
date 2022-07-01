package com.finance.createIndicatorData.model;


import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Builder
@Table("dataOfIndicators")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfIndicator {

    @Id
    Long id;

    List<Integer> decisionByDeal;

    IndicatorType indicatorType;

    CurrencyPair currencyPair;

    TimeFrame timeFrame;


}
