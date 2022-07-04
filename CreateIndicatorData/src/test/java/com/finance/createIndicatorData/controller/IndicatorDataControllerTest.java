package com.finance.createIndicatorData.controller;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.dto.ResponseDataOfStrategy;
import com.finance.createIndicatorData.intagration.IntegrationTestBased;
import com.finance.strategyDescriptionParameters.*;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class IndicatorDataControllerTest extends IntegrationTestBased {


    ObjectMapper objectMapper;
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void generateDataOfIndicators() {

        RequestDataOfStrategy requestDataOfStrategy = RequestDataOfStrategy.builder()
                .typeOfDeal(TypeOfDeal.BUY)
                .candlesInformation(CandlesInformation.builder().currencyPair(CurrencyPair.EURUSD).timeFrame(TimeFrame.M1).build())
                // TODO добавить информацию и валидацию полей
                .descriptionToOpenADeal(DescriptionToOpenADeal.builder().indicators(List.of()).build())
                .descriptionToCloseADeal(DescriptionToCloseADeal.builder().indicators(List.of()).build())
                .build();
        DataOfCurrencyPair dataOfCurrencyPair = DataOfCurrencyPair.builder()
                .currencyPair(CurrencyPair.EURUSD)
                .timeFrame(TimeFrame.M1)
                .closingPrices(List.of())
                .openingPrices(List.of())
                .highPrices(List.of())
                .lowPrices(List.of()).build();
        ResponseDataOfStrategy responseDataOfStrategy = ResponseDataOfStrategy.of(dataOfCurrencyPair, new Byte[]{},
                new Byte[]{});

        String request = objectMapper.writeValueAsString(requestDataOfStrategy);
        String response = objectMapper.writeValueAsString(responseDataOfStrategy);
        mockMvc.perform(post("/generateDataOfIndicators").contentType(ContentType.APPLICATION_JSON.getMimeType()).content(request)).
                andExpectAll(
                        status().is3xxRedirection()
//                        MockMvcResultMatchers.content().json(response)
                );
    }
}
