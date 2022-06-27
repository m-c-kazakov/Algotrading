package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;

import java.util.List;
import java.util.Map;

public interface FinalSolutionGenerator {
    Map<TimeFrame, List<List<Integer>>> getGroupedByTimeFrameIndicatorsDecision(RequestDataOfStrategy request,
                                                                                Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap,
                                                                                List<Indicator> indicatorsOfDescriptionToOpenADeal);

    Map<TimeFrame, List<Integer>> getDecisionByTimeFrame(
            Map<TimeFrame, List<List<Integer>>> groupedByTimeFrameIndicatorsDecision);

    List<Integer> generateFinalDecision(List<List<Integer>> indicatorDecisions);

    void checkingTheEqualityOfListSizes(Map<TimeFrame, List<List<Integer>>> groupedByTimeFrameIndicatorsDecision);

    List<List<Integer>> convertIndicatorDataToTheSmallestTimeFrame(
            TimeFrame theSmallestTimeFrame, Map<TimeFrame, List<Integer>> decisionByTimeFrame);


    List<Integer> convertIndicatorDataToTheDesiredTimeFrame(TimeFrame theSmallestTimeFrame,
                                                            TimeFrame timeFrame,
                                                            List<Integer> indicatorDecisions);
}
