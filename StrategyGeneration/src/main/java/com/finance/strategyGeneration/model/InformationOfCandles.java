package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@With
@RequiredArgsConstructor
@Table("information_of_candles")
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfCandles {
    @Id
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Long id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    String hashCode;
    CurrencyPair currencyPair;
    TimeFrame timeFrame;
}
