package com.finance.createIndicatorData.service;

import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

/**
 * Задача класса последовательно сконвертировать данные из одного TimeFrame в требуемый через несколько конвертеров
 * Пример: D -> H1 -> M1 -> S1
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfIndicatorConverterToAnotherTimeFrameImpl implements DataOfIndicatorConverterToAnotherTimeFrame {


    static Map<TimeFrame, Map<String, List<Integer>>> constantsForTimeFrame = Map.of(
            TimeFrame.H1, Map.of(
                    "0", List.of(0, 0),
                    "1", List.of(
                            Integer.parseInt("1".repeat(TimeFrame.M1.getBatchSize()), 2),
                            Integer.parseInt("1".repeat(TimeFrame.M1.getBatchSize()), 2))
            )
    );
    Map<TimeFrame, Function<List<String>, ConvertedResult>> converters = Map.of(
            TimeFrame.H1, this::convertH1ToM1
    );

    @Override
    public List<Integer> convert(final TimeFrame desiredTimeFrame, TimeFrame currentTimeFrame,
                                 String binaryStringIndicatorDecisions) {

        List<String> convertedResultList = List.of(binaryStringIndicatorDecisions);

        for (int i = 0; i < currentTimeFrame.getOrder(); i++) {
            if (desiredTimeFrame == currentTimeFrame) {
                break;
            }

            TimeFrame finalCurrentTimeFrame = currentTimeFrame;
            Function<List<String>, ConvertedResult> converter = ofNullable(
                    converters.get(currentTimeFrame)).orElseThrow(
                    () -> new RuntimeException("Не найдена функция для обработки TimeFrame=" + finalCurrentTimeFrame));

            ConvertedResult convertedResult = converter.apply(convertedResultList);

            currentTimeFrame = convertedResult.timeFrame;
            convertedResultList = convertedResult.binaryStringIndicatorDecisions;

        }

        Assert.notEmpty(convertedResultList,
                "Результат конвертации не может быть пустым. desiredTimeFrame=%s; currentTimeFrame=%s; binaryStringIndicatorDecisions=%s".formatted(
                        desiredTimeFrame, currentTimeFrame, binaryStringIndicatorDecisions));


        return convertedResultList.stream()
                .map(s -> Integer.parseInt(s, 2))
                .toList();
    }

    public ConvertedResult convertH1ToM1(List<String> binaryStrings) {
        List<String> convertedIndicatorDecisions = binaryStrings.stream()
                .map(s -> Arrays.stream(s.split(""))
                        .map(el -> {
                            // Часы в минуты
                            if ("0".equals(el)) {
                                return ofNullable(constantsForTimeFrame.get(TimeFrame.H1)).map(map -> map.get("0"))
                                        .orElseThrow(() -> new RuntimeException(
                                                "Не найдено значение для подстановки TimeFrame=H1, value=0"));
                            } else if ("1".equals(el)) {
                                return ofNullable(constantsForTimeFrame.get(TimeFrame.H1)).map(map -> map.get("0"))
                                        .orElseThrow(() -> new RuntimeException(
                                                "Не найдено значение для подстановки TimeFrame=H1, value=1"));
                            } else {
                                throw new RuntimeException("Получено не ожидаемое число = " + el);
                            }
                        })
                        .flatMap(List::stream)
                        .toList()
                )
                .flatMap(List::stream)
                .map(Integer::toBinaryString)
                .toList();

        return new ConvertedResult(TimeFrame.M1, convertedIndicatorDecisions);

    }

    @Getter
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    class ConvertedResult {
        TimeFrame timeFrame;
        List<String> binaryStringIndicatorDecisions;
    }
}
