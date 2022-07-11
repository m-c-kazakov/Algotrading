package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.creator.InformationOfCandlesCreator;
import com.finance.strategyGeneration.repository.InformationOfCandlesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

        InformationOfCandles entity = InformationOfCandles.builder()
                .timeFrame(timeFrame)
                .currencyPair(currencyPair)
                .build();

        return create(entity);
    }

    @Override
    public InformationOfCandles createWithNewTimeFrame(String informationOfCandlesId, TimeFrame timeFrame) {
        InformationOfCandles informationOfCandles = repository.findById(Long.valueOf(informationOfCandlesId))
                .orElseThrow(() -> new RuntimeException("Не найден CandlesInformation для Id == " + informationOfCandlesId));
        return create(informationOfCandles.withTimeFrame(timeFrame));
    }

    @Override
    public InformationOfCandles create(InformationOfCandles informationOfCandles) {
        InformationOfCandles entity = InformationOfCandlesCreator.createWithHashCode(informationOfCandles);

        return repository
                .findByHashCode(entity.getHashCode())
                .orElseGet(() -> repository.save(entity.withId(null)));

    }

    @Override
    public Optional<InformationOfCandles> findByHashCode(String hashCode) {
        return repository.findByHashCode(hashCode);
    }
}
