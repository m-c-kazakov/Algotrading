package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.repository.InformationOfIndicatorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public InformationOfIndicator create(InformationOfIndicator informationOfIndicator) {
        InformationOfIndicator entity = Optional.ofNullable(
                        informationOfIndicator.getHashCode())
                .map(integer -> informationOfIndicator)
                .orElseGet(() -> informationOfIndicator.withHashCode(informationOfIndicator.hashCode()));
        return repository.findByHashCode(entity.getHashCode())
                .orElseGet(() -> repository.save(entity));
    }

    @Override
    public List<InformationOfIndicator> findAllById(List<String> descriptionToOpenADeal) {
        List<Long> ids = descriptionToOpenADeal.stream().map(Long::valueOf).toList();
        return repository.findAllByIdIn(ids);
    }
}
