package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.repository.InformationOfCandlesRepository;
import com.finance.strategyGeneration.service.mapper.InformationOfCandlesMapper;
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
public class CandlesInformationServiceImpl implements CandlesInformationService {

    InformationOfCandlesMapper mapper;
    InformationOfCandlesRepository repository;

    @Override
    public CandlesInformation findById(long candlesInformationId) {
        return repository.findById(candlesInformationId)
                .map(mapper::mapTo)
                .orElseThrow(
                        () -> new RuntimeException("Не найден CandlesInformation для Id == " + candlesInformationId));
    }

    @Override
    public CandlesInformation save(TimeFrame timeFrame, CurrencyPair currencyPair) {
        int hashCode = Objects.hashCode(timeFrame, currencyPair);

        InformationOfCandles informationOfCandles = repository.findByHashCode(hashCode)
                .orElseGet(() -> repository.save(InformationOfCandles.builder()
                        .hashCode(hashCode)
                        .timeFrame(timeFrame)
                        .currencyPair(currencyPair)
                        .build()));
        return mapper.mapTo(informationOfCandles);
    }
}
