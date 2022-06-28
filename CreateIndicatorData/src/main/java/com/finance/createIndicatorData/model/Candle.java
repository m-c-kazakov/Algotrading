package com.finance.createIndicatorData.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@RequiredArgsConstructor
@Table("candles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Candle {

    @Id
    Long id;
    CurrencyPair ticker;
    int per;
    Date date;
    Timestamp time;
    BigDecimal open;
    BigDecimal high;
    BigDecimal low;
    BigDecimal close;
    int vol;
}
