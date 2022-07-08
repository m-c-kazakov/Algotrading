package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@RequiredArgsConstructor
@Table("information_of_candles")
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfCandles {
    @Id
    @EqualsAndHashCode.Exclude
    long id;
    @EqualsAndHashCode.Exclude
    long hashCode;
    CurrencyPair currencyPair;
    TimeFrame timeFrame;
}
