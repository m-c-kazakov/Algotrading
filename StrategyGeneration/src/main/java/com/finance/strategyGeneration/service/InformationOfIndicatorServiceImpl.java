package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.creator.InformationOfCandlesStorageCreator;
import com.finance.strategyGeneration.repository.InformationOfIndicatorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static java.util.Objects.isNull;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfIndicatorServiceImpl implements InformationOfIndicatorService {

    InformationOfIndicatorRepository repository;
    InformationOfCandleService informationOfCandleService;


    // Todo возможно стоит удалить
    @Override
    public List<InformationOfIndicator> findAllById(List<String> strindIds) {
        Assert.notEmpty(strindIds, "Коллекция id не может быть пустой");
        List<Long> ids = strindIds.stream().map(Long::valueOf).toList();
        return repository.findAllByIdIn(ids).stream().map(this::addInformationOfCandles).toList();
    }

    @Override
    @Cacheable(cacheNames = "createInformationOfIndicator", key = "#informationOfIndicator.hashCode")
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public InformationOfIndicator create(InformationOfIndicator informationOfIndicator) {

        InformationOfIndicator entity = repository
                .findByHashCode(informationOfIndicator.getHashCode())
                .map(indicator -> indicator.withInformationOfCandles(informationOfIndicator.getInformationOfCandles()))
                .orElseGet(() -> repository.save(informationOfIndicator.withId(null)));

        if (isNull(entity.getInformationOfCandles()) || isNull(entity.receiveTimeFrame()) || isNull(
                entity.receiveCurrencyPair())) {
            entity = addInformationOfCandles(entity);
        }
        Assert.notNull(entity.getId(), "InformationOfIndicator id не может быть null");
        Assert.notNull(entity.receiveInformationOfCandlesId(), "InformationOfCandlesId id не может быть null");
        return entity;
    }

    private InformationOfIndicator addInformationOfCandles(InformationOfIndicator informationOfIndicator) {

        InformationOfCandles informationOfCandles =
                informationOfCandleService.findById(informationOfIndicator.receiveInformationOfCandlesId());

        InformationOfIndicator result = informationOfIndicator.withInformationOfCandles(
                InformationOfCandlesStorageCreator.create(informationOfCandles));
        Assert.notNull(result.receiveTimeFrame(), "TimeFrame не может быть null.");
        Assert.notNull(result.receiveCurrencyPair(), "CurrencyPair не может быть null.");
        return result;
    }
}
