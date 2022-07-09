package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

/**
 * Методы скрещивания: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */

@Slf4j
@Component
public class TypesOfCrossesImpl implements TypesOfCrosses {

    @Override
    public List<Indicator> singlePointCrossing(List<Indicator> first, List<Indicator> second, int separator) {
        if (first.isEmpty()) {
            throw new RuntimeException("first");
        } else if (second.isEmpty()) {
            throw new RuntimeException("second");
        }
        // TODO в тестах проверить что будет если придут коллекции с 0 и 1 количеством элементов
        List<Indicator> firstSubList = first.subList(0, separator);
        List<Indicator> secondSubList = second.subList(separator, second.size());
        return Stream.of(firstSubList, secondSubList).flatMap(List::stream).toList();
    }
}
