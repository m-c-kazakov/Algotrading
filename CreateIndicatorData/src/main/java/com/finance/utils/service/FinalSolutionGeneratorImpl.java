package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.dto.RequestDataOfStrategy;
import com.finance.utils.model.DataOfIndicator;
import com.finance.utils.service.converterToDataOfIndicator.BinaryInteger;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FinalSolutionGeneratorImpl implements FinalSolutionGenerator {


    IndicatorDataGenerator indicatorDataGenerator;
    DataOfIndicatorConverterToAnotherTimeFrame dataOfIndicatorConverterToAnotherTimeFrame;

    @Override
    public Map<TimeFrame, List<List<Integer>>> getGroupedByTimeFrameIndicatorsDecision(RequestDataOfStrategy request,
                                                                                       Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap,
                                                                                       List<Indicator> indicatorsOfDescriptionToOpenADeal) {
        return indicatorsOfDescriptionToOpenADeal.stream()
                .map(indicator -> indicatorDataGenerator.generate(indicator, request.getTypeOfDeal(),
                        dataOfCurrencyPairMap.get(indicator.candlesInformationToString())))
                .collect(Collectors.groupingBy(DataOfIndicator::getTimeFrame,
                        Collectors.mapping(DataOfIndicator::getDecisionByDeal, Collectors.toList())));
    }

    @Override
    public Map<TimeFrame, List<Integer>> getDecisionByTimeFrame(
            Map<TimeFrame, List<List<Integer>>> groupedByTimeFrameIndicatorsDecision) {
        Map<TimeFrame, List<Integer>> decisionByTimeFrame = new EnumMap<>(TimeFrame.class);

        for (Map.Entry<TimeFrame, List<List<Integer>>> entry : groupedByTimeFrameIndicatorsDecision.entrySet()) {
            List<List<Integer>> indicatorDecisions = entry.getValue()
                    .stream()
                    .toList();

            List<Integer> decisions = generateFinalDecision(indicatorDecisions);


            decisionByTimeFrame.put(entry.getKey(), decisions);
        }
        return decisionByTimeFrame;
    }

    @Override
    public List<Integer> generateFinalDecision(List<List<Integer>> indicatorDecisions) {
        Assert.state(indicatorDecisions.stream().map(List::size).collect(Collectors.toSet()).size() == 1, "Коллекции indicatorDecisions не могут быть разного размера.");
        return Stream
                .iterate(0,
                        index -> index < indicatorDecisions.get(0).size(),
                        index -> index + 1)
                .map(index -> indicatorDecisions.stream()
                        .map(integers -> integers.get(index))
                        .reduce((integer, integer2) -> integer & integer2)
                        .orElseThrow(
                                () -> new RuntimeException("Не сформирован итоговой результат работы индикаторов")))
                .toList();
    }

    @Override
    public void checkingTheEqualityOfListSizes(
            Map<TimeFrame, List<List<Integer>>> groupedByTimeFrameIndicatorsDecision) {
        for (Map.Entry<TimeFrame, List<List<Integer>>> entry : groupedByTimeFrameIndicatorsDecision.entrySet()) {

            int size = entry.getValue()
                    .stream()
                    .map(List::size)
                    .collect(Collectors.toSet())
                    .size();

            if (size != 1) {
                throw new RuntimeException("Коллекции обладают разным размером");
            }
        }
    }

    @Override
    public List<List<Integer>> convertIndicatorDataToTheSmallestTimeFrame(
            TimeFrame theSmallestTimeFrame, Map<TimeFrame, List<Integer>> decisionByTimeFrame) {


        return decisionByTimeFrame.entrySet()
                .stream()
                .map(entry -> this.convertIndicatorDataToTheDesiredTimeFrame(
                        theSmallestTimeFrame,
                        entry.getKey(),
                        entry.getValue()))
                .toList();
    }

    @Override
    public List<Integer> convertIndicatorDataToTheDesiredTimeFrame(TimeFrame theSmallestTimeFrame,
                                                                   TimeFrame timeFrame,
                                                                   List<Integer> indicatorDecisions) {

        return indicatorDecisions.stream()
                .map(Integer::toBinaryString)
                .map(binaryString -> BinaryInteger.restoreBinarySize(timeFrame.getBatchSize(), binaryString))
                .map(binaryString -> dataOfIndicatorConverterToAnotherTimeFrame.convert(theSmallestTimeFrame, timeFrame,
                        binaryString))
                .flatMap(List::stream)
                .toList();
    }
}
