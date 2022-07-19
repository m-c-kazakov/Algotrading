package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.*;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@With
@Value
@Builder
@Table("information_of_candles")
public class InformationOfCandles {
    @Id
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Long id;
    CurrencyPair currencyPair;
    TimeFrame timeFrame;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NonFinal
    String hashCode;

    @Column("hash_code")
    public String getHashCode() {
        // TODO понять как лениво инициализировать поле при обращении к нему. Spring Data JDBC не использует getter для получения данных
        this.hashCode = String.join("_", currencyPair.name(), timeFrame.name());
        return hashCode;
    }
}
