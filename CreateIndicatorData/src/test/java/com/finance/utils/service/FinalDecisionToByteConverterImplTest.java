package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.TimeFrame;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalDecisionToByteConverterImplTest {

    FinalDecisionToByteConverterImpl finalDecisionToByteConverter = new FinalDecisionToByteConverterImpl();


    @Test
    void execute() {
        List<Integer> finalDecision = List.of(0, 1071644668);
        TimeFrame timeFrame = TimeFrame.M1;
        List<Byte> byteResult = finalDecisionToByteConverter.execute(timeFrame, finalDecision);

        assertThat(byteResult)
                .isNotEmpty()
                .hasSize(timeFrame.getBatchSize() * finalDecision.size());




    }
}