package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.dto.ResponseDataOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfStrategyGenerationServiceImpl implements DataOfStrategyGenerationService {

    DataOfCurrencyPairInitializer dataOfCurrencyPairInitializer;
    DealDecisionService dealDecisionService;
    DataOfCurrencyPairService dataOfCurrencyPairService;


    @Override
    public ResponseDataOfStrategy generate(RequestDataOfStrategy request) {

        // Определить данные какой валютной пары с каким таймфреймом нужны
        Set<DataOfCurrencyPair> dataOfCurrencyPairs = dataOfCurrencyPairInitializer.execute(request);

        // Получить необходимые данные
        Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap = dataOfCurrencyPairs.stream()
                .map(dataOfCurrencyPairService::getCurrencyPair)
                .collect(Collectors.toUnmodifiableMap(
                        DataOfCurrencyPair::candlesInformationToString,
                        dataOfCurrencyPair -> dataOfCurrencyPair));


        // Отдать данные валютных пар на обработку индикаторам
        // свести результат работы индикаторов к 1 массиву
        Byte[] decisionOnOpeningDeal = dealDecisionService.makeDecisionOnOpeningDeal(request, dataOfCurrencyPairMap);

        String candleWithSmallestTimeFrame = request.getCandlesInformation().toString();
        DataOfCurrencyPair dataOfCurrencyPair = Optional.ofNullable(
                dataOfCurrencyPairMap.get(candleWithSmallestTimeFrame)).orElseThrow(
                () -> new RuntimeException("Не найдены данные свечи с параметрами=" + candleWithSmallestTimeFrame));
        // собрать response

        // TODO Добавить решения по закрытию сделки
        return ResponseDataOfStrategy.of(dataOfCurrencyPair, decisionOnOpeningDeal, new Byte[]{});
    }
}
