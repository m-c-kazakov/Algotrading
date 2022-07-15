package com.finance.utils.repository;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.utils.intagration.IntegrationTestBased;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CandleRepositoryTest extends IntegrationTestBased {

    @Autowired
    CandleRepository candleRepository;


    @Test
    void findAllByTickerAndPer() {

        assertThat(candleRepository.findAllByTickerAndPer(CurrencyPair.EURUSD, 1))
                .isNotEmpty()
                .hasSize(7);
    }
}