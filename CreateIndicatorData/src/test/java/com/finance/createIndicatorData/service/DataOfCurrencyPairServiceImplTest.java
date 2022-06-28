package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.intagration.IntegrationTestBased;
import com.finance.createIndicatorData.model.Candle;
import com.finance.createIndicatorData.repository.CandleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DataOfCurrencyPairServiceImplTest extends IntegrationTestBased {

    @Autowired
    CandleRepository candleRepository;

    @Test
    void getCurrencyPair() {
        Iterable<Candle> all = candleRepository.findAll();
    }
}