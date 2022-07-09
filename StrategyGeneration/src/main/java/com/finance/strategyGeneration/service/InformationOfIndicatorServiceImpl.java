package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.repository.InformationOfIndicatorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfIndicatorServiceImpl implements InformationOfIndicatorService {

    InformationOfIndicatorRepository repository;

    // TODO ДОБАВИТЬ КЭШИРОВАНИЕ
    @Override
    public InformationOfIndicator findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдена сущность Indicator с Id=" + id));
    }

    @Override
    public List<InformationOfIndicator> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public InformationOfIndicator create(InformationOfIndicator informationOfIndicator) {
        return repository.findByHashCode(informationOfIndicator.getHashCode())
                .orElseGet(() -> {
                    InformationOfIndicator entity = Optional.ofNullable(
                                    informationOfIndicator.getHashCode())
                            .map(integer -> informationOfIndicator)
                            .orElseGet(() -> informationOfIndicator.withHashCode(informationOfIndicator.hashCode()));
                    return repository.save(entity);
                });
    }

    @Override
    public InformationOfIndicator create(IndicatorType indicatorType,
                                         InformationOfCandles informationOfCandles,
                                         Map<String, String> parameters) {
        return create(InformationOfIndicator.builder()
                .indicatorType(indicatorType)
                .informationOfCandlesId(String.valueOf(informationOfCandles.getId()))
                .build());
    }
}
