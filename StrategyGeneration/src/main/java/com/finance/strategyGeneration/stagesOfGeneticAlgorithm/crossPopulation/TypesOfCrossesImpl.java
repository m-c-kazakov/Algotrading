package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Stream;

/**
 * Методы скрещивания: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */

@Slf4j
@Component
public class TypesOfCrossesImpl implements TypesOfCrosses {

    @Override
    public List<InformationOfIndicator> singlePointCrossing(List<InformationOfIndicator> first, List<InformationOfIndicator> second, int separator) {
        Assert.notEmpty(first, "Коллекция индикаторов first не может быть пустой");
        Assert.notEmpty(second, "Коллекция индикаторов second не может быть пустой");

        List<InformationOfIndicator> firstSubList = first.subList(0, separator);
        List<InformationOfIndicator> secondSubList = second.subList(separator, second.size());
        return Stream.of(firstSubList, secondSubList).flatMap(List::stream).toList();
    }
}
