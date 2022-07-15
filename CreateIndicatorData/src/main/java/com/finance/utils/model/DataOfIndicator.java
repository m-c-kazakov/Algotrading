package com.finance.utils.model;


import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@Table("data_of_indicators")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfIndicator {

    @Id
    Long id;

    List<Integer> decisionByDeal;

    IndicatorType indicatorType;

    CurrencyPair currencyPair;

    TimeFrame timeFrame;


}
