package com.finance.createIndicatorData;

import com.finance.createIndicatorData.dao.DataOfCurrencyPairDao;
import com.finance.createIndicatorData.model.DataOfCurrencyPair;
import com.finance.createIndicatorData.service.DataOfCurrencyPairInitializer;
import com.finance.createIndicatorData.service.DealDecisionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfStrategyGenerationServiceImpl implements DataOfStrategyGenerationService {

    DataOfCurrencyPairInitializer dataOfCurrencyPairInitializer;
    DataOfCurrencyPairDao dataOfCurrencyPairDao;
    private DealDecisionService dealDecisionService;

    @Override
    public ResponseDataOfStrategy generate(RequestDataOfStrategy request) {

        // Определить данные какой валютной пары с каким таймфреймом нужны
        Set<DataOfCurrencyPair> dataOfCurrencyPairs = dataOfCurrencyPairInitializer.execute(request);

        // Получить необходимые данные
        Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap = dataOfCurrencyPairs.stream()
                .map(dataOfCurrencyPairDao::getDataOfCurrencyPair)
                .collect(
                        Collectors.toUnmodifiableMap(DataOfCurrencyPair::candlesInformationToString, value -> value));


        // Отдать данные валютных пар на обработку индикаторам
        // свести результат работы индикаторов к 1 массиву
        Byte[] decisionOnOpeningDeal = dealDecisionService.makeDecisionOnOpeningDeal(request, dataOfCurrencyPairMap);


        // собрать response
        return null;
    }
}
