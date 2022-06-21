package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.RequestDataOfStrategy;
import com.finance.createIndicatorData.model.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.DataOfIndicator;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DealDecisionServiceImpl implements DealDecisionService {
    IndicatorDataGenerator indicatorDataGenerator;

    public static void main(String[] args) {
        int i1 = 1;
        System.out.println(Integer.toBinaryString(i1));
        int i2 = 5;
        System.out.println(Integer.toBinaryString(i2));
        int i3 = 3;
        System.out.println(Integer.toBinaryString(i3));
        System.out.println(i1 & i2 & i3);
    }

    @Override
    public byte[] makeDecisionOnOpeningDeal(RequestDataOfStrategy request,
                                            Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap) {

        // получить коллецию всех индикаторов на открытие сделки
        List<Indicator> indicatorsOfDescriptionToOpenADeal = request.getIndicatorsOfDescriptionToOpenADeal();

        // Получить решение по всем индикаторам
        // Сгрупировать решения по TimeFrame
        Map<TimeFrame, List<DataOfIndicator>> groupedByTimeFrameIndicatorsDecision = indicatorsOfDescriptionToOpenADeal.stream()
                .map(indicator -> indicatorDataGenerator.generate(indicator, request.getTypeOfDeal(),
                        dataOfCurrencyPairMap.get(indicator.candlesInformationToString())))
                .collect(Collectors.groupingBy(DataOfIndicator::getTimeFrame));


        // с помощью логической операции и & сформировать итоговое решение по решениям индикаторов в рамках одного таймфрейма
        Map<TimeFrame, Integer> decisionByTimeFrame = new HashMap<>();
        for (Map.Entry<TimeFrame, List<DataOfIndicator>> entry : groupedByTimeFrameIndicatorsDecision.entrySet()) {
            List<List<Integer>> indicatorDecisions = entry.getValue().stream().map(DataOfIndicator::getDecisionByDeal).toList();
            // TODO сделать проверку на то что длинна всех коллекций одинаковая
            for (int i = 0; i < indicatorDecisions.size(); i++) {
                int cursor = i;
                Integer decision = indicatorDecisions.stream()
                        .map(integers -> integers.get(cursor)).
                        reduce((integer, integer2) -> integer & integer2)
                        .orElseThrow(
                                () -> new RuntimeException("Не сформирован итоговой результат работы индикаторов"));
                decisionByTimeFrame.put(entry.getKey(), decision);
            }

        }


        // Сформировать итоговое решение по решениям индикаторов их разных таймфремов



        return new byte[0];
    }
}
