package com.finance.strategyGeneration.crossing;

import java.util.List;
import java.util.stream.Stream;

/**
 * Методы скрещивания: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */

public class TypesOfCrosses {
    // TODO реализовать другие методы скрещивания

    public static <T> List<T> singlePointCrossing(List<T> first, List<T> second, int separator) {
        // TODO в тестах проверить что будет если придут коллекции с 0 и 1 количеством элементов
        List<T> firstSubList = first.subList(0, separator);
        List<T> secondSubList = second.subList(separator, second.size());
        return Stream.of(firstSubList, secondSubList).flatMap(List::stream).toList();
    }
}
