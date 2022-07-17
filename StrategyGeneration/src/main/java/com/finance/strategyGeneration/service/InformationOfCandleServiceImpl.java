package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.repository.InformationOfCandlesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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
    @Cacheable(cacheNames = "findByIdInformationOfCandles", key = "#candlesInformationId")
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public InformationOfCandles findById(long candlesInformationId) {
        return repository.findById(candlesInformationId)
                .orElseThrow(
                        () -> new RuntimeException("Не найден CandlesInformation для Id == " + candlesInformationId));
    }

    @Override
    @Cacheable(cacheNames = "createInformationOfCandles", key = "#currencyPair+'_'+#timeFrame")
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public InformationOfCandles create(TimeFrame timeFrame, CurrencyPair currencyPair) {

        InformationOfCandles entity = InformationOfCandles.builder()
                .timeFrame(timeFrame)
                .currencyPair(currencyPair)
                .build();

        return create(entity);
    }

    @Override
    @Cacheable(cacheNames = "createInformationOfCandles", key = "#informationOfCandles.currencyPair+'_'+#informationOfCandles.timeFrame")
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public InformationOfCandles create(InformationOfCandles informationOfCandles) {

        return repository
                .findByHashCode(informationOfCandles.getHashCode())
                .orElseGet(() -> repository.save(informationOfCandles.withId(null)));

    }
}
