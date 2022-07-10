package com.finance.strategyGeneration.model.creator;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.InformationOfCandles;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@FieldDefaults(level = AccessLevel.PACKAGE)
class InformationOfIndicatorCreatorTest {

    InformationOfIndicatorCreator informationOfIndicatorCreator;

    @BeforeEach
    public void beforeEach() {
        informationOfIndicatorCreator = new InformationOfIndicatorCreator();
    }

    @Test
    void createSuccessTest() {
        assertThat(informationOfIndicatorCreator.create(IndicatorType.SMA, InformationOfCandles.builder().currencyPair(
                CurrencyPair.EURUSD).timeFrame(TimeFrame.M1).build(), Map.of("qwe", "qwe"))).isNotNull();

    }

    @Test
    void createFailTest() {
        assertThrows(IllegalArgumentException.class, () -> informationOfIndicatorCreator.create(IndicatorType.SMA,
                InformationOfCandles.builder()
                        .currencyPair(CurrencyPair.EURUSD)
                        .timeFrame(TimeFrame.M1)
                        .build(), Map.of()));

    }
}