package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.repository.InformationOfCandlesRepository;
import com.google.common.base.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfCandleServiceImpl implements InformationOfCandleService {

    InformationOfCandlesRepository repository;

    @Override
    public InformationOfCandles findById(long candlesInformationId) {
        return repository.findById(candlesInformationId)
                .orElseThrow(
                        () -> new RuntimeException("Не найден CandlesInformation для Id == " + candlesInformationId));
    }

    @Override
    public InformationOfCandles create(TimeFrame timeFrame, CurrencyPair currencyPair) {
        int hashCode = Objects.hashCode(timeFrame, currencyPair);

        return repository.findByHashCode(hashCode)
                .orElseGet(() -> repository.save(InformationOfCandles.builder()
                        .hashCode(hashCode)
                        .timeFrame(timeFrame)
                        .currencyPair(currencyPair)
                        .build()));
    }

    @Override
    public InformationOfCandles createWithNewTimeFrame(String informationOfCandlesId, TimeFrame timeFrame) {
        InformationOfCandles informationOfCandles = repository.findById(Long.valueOf(informationOfCandlesId))
                .orElseThrow(
                        () -> new RuntimeException("Не найден CandlesInformation для Id == " + informationOfCandlesId));
        return repository.save(informationOfCandles.withTimeFrame(timeFrame));
    }
}
