package com.finance.utils.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Time;
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
    Time time;
    BigDecimal open;
    BigDecimal high;
    BigDecimal low;
    BigDecimal close;
    int vol;
}
