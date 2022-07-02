package com.finance.createIndicatorData.repository;

import com.finance.createIndicatorData.intagration.IntegrationTestBased;
import com.finance.createIndicatorData.model.DataOfIndicator;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DataOfIndicatorRepositoryTest extends IntegrationTestBased {

    @Autowired
    DataOfIndicatorRepository dataOfIndicatorRepository;
    @Autowired
    CandleRepository candleRepository;

    @Test
    void getDataOfIndicatorByIndicatorTypeAndAndCurrencyPairAndTimeFrame() {



        assertThat(dataOfIndicatorRepository.getDataOfIndicatorByIndicatorTypeAndAndCurrencyPairAndTimeFrame(
                IndicatorType.SMA.name(), CurrencyPair.EURUSD.name(), TimeFrame.M1.name()))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(new DataOfIndicator(1L, List.of(99999, 7777777), IndicatorType.SMA, CurrencyPair.EURUSD,
                        TimeFrame.M1));
    }
}