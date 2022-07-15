package com.finance.utils.service.converterToDataOfIndicator.indicators;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SMAIndicatorImplTest {

    SMAIndicatorImpl smaIndicator = new SMAIndicatorImpl();

    @Test
    void calculateSma() {

        int period = 2;
        int cursor = 2;
        List<Integer> dataOfCandle = List.of(0, 1, 2, 3, 4, 5);
        int sma = smaIndicator.calculateSma(period, dataOfCandle, cursor);
        assertEquals(1, sma);

    }

    @Test
    void sequentialGenerationSmaResult() {
    }

    @Test
    void parallelGenerationSmaResult() {
    }

    @Test
    void getDecision() {
    }
}