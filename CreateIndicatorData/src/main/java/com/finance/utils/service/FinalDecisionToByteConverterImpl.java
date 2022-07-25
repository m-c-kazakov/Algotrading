package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.TimeFrame;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FinalDecisionToByteConverterImpl implements FinalDecisionToByteConverter {
    @Override
    public List<Byte> execute(TimeFrame timeFrame, List<Integer> finalDecision) {
        return finalDecision.stream()
                .map(Integer::toBinaryString)
                .map(value -> {
                    if (value.length() < timeFrame.getBatchSize()) {
                        return "0".repeat(timeFrame.getBatchSize() - value.length()) + value;
                    }
                    return value;
                })
                .flatMap(binaryStringInt -> Arrays.stream(binaryStringInt.split("")))
                .map(Byte::parseByte)
                .toList();
    }
}
