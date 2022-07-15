package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.dto.RequestDataOfStrategy;
import com.finance.utils.service.converterToDataOfIndicator.IndicatorUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DealDecisionServiceImpl implements DealDecisionService {

    FinalSolutionGenerator finalSolutionGenerator;
    IndicatorUtils indicatorUtils;

    @Override
    public List<Byte> makeDecisionOnOpeningDeal(RequestDataOfStrategy request,
                                                Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap) {

        // получить коллецию всех индикаторов на открытие сделки
        List<Indicator> indicatorsOfDescriptionToOpenADeal = request.getIndicatorsOfDescriptionToOpenADeal();

        // Получить решение по всем индикаторам
        // Сгрупировать решения по TimeFrame
        Map<TimeFrame, List<List<Integer>>> groupedByTimeFrameIndicatorsDecision = finalSolutionGenerator.getGroupedByTimeFrameIndicatorsDecision(
                request,
                dataOfCurrencyPairMap, indicatorsOfDescriptionToOpenADeal);

        // Проверить что все коллекции имеют одинаковый размер
        finalSolutionGenerator.checkingTheEqualityOfListSizes(groupedByTimeFrameIndicatorsDecision);


        // с помощью логической операции и & сформировать итоговое решение по решениям индикаторов в рамках одного таймфрейма
        Map<TimeFrame, List<Integer>> decisionByTimeFrame = finalSolutionGenerator.getDecisionByTimeFrame(
                groupedByTimeFrameIndicatorsDecision);

        // Привести все результаты работы индикаторов к одному таймфрейму
        List<List<Integer>> timeFrameListMap = finalSolutionGenerator.convertIndicatorDataToTheSmallestTimeFrame(
                request.getTheSmallestTimeFrame(), decisionByTimeFrame);

        // Сформировать итоговое решение по решениям индикаторов в разных таймфремах
        List<Integer> finalDecision = finalSolutionGenerator.generateFinalDecision(timeFrameListMap);

        // Обрезать начало итогового решения
        List<Integer> trimFinalDecision = indicatorUtils.trimPercentageOfArray(finalDecision, 5);

        // создать итоговое решение в byte
        return trimFinalDecision.stream()
                .map(Integer::toBinaryString)
                .flatMap(binaryStringInt -> Arrays.stream(binaryStringInt.split("")))
                .map(Byte::parseByte).toList();
    }
}
