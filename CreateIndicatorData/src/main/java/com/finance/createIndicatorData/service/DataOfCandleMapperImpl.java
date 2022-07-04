package com.finance.createIndicatorData.service;

import com.finance.dataHolder.DataOfCandle;
import com.finance.strategyDescriptionParameters.TypeOfBar;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataOfCandleMapperImpl implements DataOfCandleMapper {

    @Override
    public List<DataOfCandle> mapTo(Map<TypeOfBar, List<Integer>> candles) {

        List<Integer> closingPrices = candles.get(TypeOfBar.CLOSE);
        List<Integer> openingPrices = candles.get(TypeOfBar.OPEN);
        List<Integer> lowPrices = candles.get(TypeOfBar.LOW);
        List<Integer> highPrices = candles.get(TypeOfBar.HIGH);

        checkCollectionsSize(closingPrices, openingPrices, lowPrices, highPrices);

        return Stream.iterate(0, integer -> integer < closingPrices.size(), integer -> integer + 1)
                .map(i -> DataOfCandle.builder()
                        .closingPrices(closingPrices.get(i))
                        .openingPrices(openingPrices.get(i))
                        .lowPrices(lowPrices.get(i))
                        .highPrices(highPrices.get(i))
                        .build())
                .toList();
    }

    private void checkCollectionsSize(List<Integer> closingPrices, List<Integer> openingPrices, List<Integer> lowPrices, List<Integer> highPrices) {
        Set<Integer> collectionsSize = Stream.of(closingPrices, openingPrices, lowPrices, highPrices).map(List::size)
                .collect(Collectors.toSet());
        if (collectionsSize.size() != 1) {
            throw new RuntimeException(
                    "Размеры коллекций с данными свечей различаются. closingPrices=%d, openingPrices=%d, lowPrices=%d, highPrices=%d".formatted(
                            closingPrices.size(), openingPrices.size(), lowPrices.size(), highPrices.size()));
        }
    }
}
