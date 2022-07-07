package com.finance.createIndicatorData.controller;

import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.intagration.IntegrationTestBased;
import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyDescriptionParameters.indicators.SmaParameters;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class IndicatorDataControllerImplTest extends IntegrationTestBased {

    ObjectMapper objectMapper;
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void generateDataOfIndicators() {

        RequestDataOfStrategy requestDataOfStrategy = RequestDataOfStrategy.builder()
                .typeOfDeal(TypeOfDeal.BUY)
                .candlesInformation(
                        CandlesInformation.builder().currencyPair(CurrencyPair.EURUSD).timeFrame(TimeFrame.M1).build())
                // TODO валидацию полей
                .descriptionToOpenADeal(List.of(
                        Indicator.builder()
                                .indicatorType(IndicatorType.SMA)
                                .candlesInformation(CandlesInformation.builder().timeFrame(TimeFrame.M1).currencyPair(CurrencyPair.EURUSD).build())
                                .parameters(Map.of(SmaParameters.CALCULATE_BY.name(), TypeOfBar.CLOSE.name(), SmaParameters.PERIOD.name(), "2"))
                                .build()))
                .descriptionToCloseADeal(List.of())
                .build();

        String request = objectMapper.writeValueAsString(requestDataOfStrategy);
        mockMvc.perform(
                        post("/generateDataOfIndicators")
                                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                                .content(request)).
                andExpectAll(
                        status().is2xxSuccessful(),
                        content().json("{\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\",\"per\":1},\"candles\":[{\"closingPrices\":107342,\"openingPrices\":107360,\"lowPrices\":107342,\"highPrices\":107360},{\"closingPrices\":107346,\"openingPrices\":107339,\"lowPrices\":107318,\"highPrices\":107350},{\"closingPrices\":107340,\"openingPrices\":107338,\"lowPrices\":107333,\"highPrices\":107345},{\"closingPrices\":107342,\"openingPrices\":107332,\"lowPrices\":107327,\"highPrices\":107343},{\"closingPrices\":107339,\"openingPrices\":107332,\"lowPrices\":107330,\"highPrices\":107360},{\"closingPrices\":107331,\"openingPrices\":107344,\"lowPrices\":107325,\"highPrices\":107355},{\"closingPrices\":107312,\"openingPrices\":107331,\"lowPrices\":107312,\"highPrices\":107342}],\"decisionToOpenADeal\":[1,1,0,0,0,0,1,1,0,1,0,0,1,1,1,1,1,1,1,1,0,1,1,0,1,0,1,0,1,1,0,1,1,1,1,1,0,0,0,1],\"decisionToCloseADeal\":[]}")
                ).andReturn();
    }
}
