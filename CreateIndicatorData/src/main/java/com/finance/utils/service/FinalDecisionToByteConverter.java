package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.TimeFrame;

import java.util.List;

public interface FinalDecisionToByteConverter {
    List<Byte> execute(TimeFrame timeFrame, List<Integer> finalDecision);
}
