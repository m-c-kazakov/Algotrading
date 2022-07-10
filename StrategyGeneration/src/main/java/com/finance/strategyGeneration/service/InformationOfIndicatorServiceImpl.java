package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.creator.InformationOfCandlesStorageCreator;
import com.finance.strategyGeneration.repository.InformationOfIndicatorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfIndicatorServiceImpl implements InformationOfIndicatorService {

    InformationOfIndicatorRepository repository;
    InformationOfCandleService informationOfCandleService;

    // TODO ДОБАВИТЬ КЭШИРОВАНИЕ
    @Override
    public InformationOfIndicator findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдена сущность Indicator с Id=" + id));
    }


    // Todo возможно стоит удалить
    @Override
    public List<InformationOfIndicator> findAllById(List<String> strindIds) {
        Assert.notEmpty(strindIds, "Коллекция id не может быть пустой");
        List<Long> ids = strindIds.stream().map(Long::valueOf).toList();
        return repository.findAllByIdIn(ids).stream().map(this::addInformationOfCandles).toList();
    }

    private InformationOfIndicator addInformationOfCandles(InformationOfIndicator informationOfIndicator) {

        InformationOfCandles informationOfCandles =
                informationOfCandleService.findById(informationOfIndicator.getInformationOfCandlesId());

        InformationOfIndicator result = informationOfIndicator.withInformationOfCandles(
                InformationOfCandlesStorageCreator.create(informationOfCandles));
        Assert.notNull(result.getTimeFrame(), "TimeFrame не может быть null.");
        Assert.notNull(result.getCurrencyPair(), "CurrencyPair не может быть null.");
        return result;
    }

    @Override
    public InformationOfIndicator create(InformationOfIndicator informationOfIndicator) {
        InformationOfIndicator entity =
                informationOfIndicator.withId(null).withHashCode(informationOfIndicator.hashCode());

        InformationOfIndicator informationOfIndicatorResult = repository
                .findByHashCode(entity.getHashCode())
                .orElseGet(() -> repository.save(entity));

        return addInformationOfCandles(informationOfIndicatorResult);
    }
}
