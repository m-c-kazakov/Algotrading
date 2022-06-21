package com.finance.createIndicatorData.service.converterToDataOfIndicator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IndicatorUtilsImplTest {

    IndicatorUtils indicatorUtils = new IndicatorUtilsImpl();

    @Test
    void trimTheArray() {

        List<Integer> ints = List.of(0, 1, 2, 3, 4, 5);
        int period = 2;
        assertThat(indicatorUtils.trimTheArray(period, ints)).hasSize(ints.size() - period);
    }
}