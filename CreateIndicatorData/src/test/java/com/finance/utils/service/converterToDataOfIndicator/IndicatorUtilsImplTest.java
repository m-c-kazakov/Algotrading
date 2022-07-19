package com.finance.utils.service.converterToDataOfIndicator;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IndicatorUtilsImplTest {

    IndicatorUtils indicatorUtils = new IndicatorUtilsImpl();

    @Test
    void trimTheArray() {

        List<Integer> ints = List.of(0, 1, 2, 3, 4, 5);
        int period = 2;
        assertThat(indicatorUtils.trimTheArray(period, ints)).hasSize(ints.size() - period);
    }

    @Test
    void increaseIndicatorResultSize() {
        int expectedSize = 11;
        int upperBound = 5;
        int templateValue = 0;
        List<Integer> integers = Stream.iterate(templateValue, i -> i < upperBound, i -> i + 1).toList();
        List<Integer> result = indicatorUtils.increaseIndicatorResultSize(integers, expectedSize);
        assertThat(result).hasSize(expectedSize);
        for (int i = templateValue; i < upperBound; i++) {
            assertThat(result.get(i)).isEqualTo(templateValue);

        }
    }

    @Test
    void trimPercentageOfArray() {
        List<Integer> integers = Stream.iterate(0, i -> i < 10, i -> i + 1).toList();

        assertThat(indicatorUtils.trimPercentageOfArray(integers, 10)).hasSize(9);

    }
}