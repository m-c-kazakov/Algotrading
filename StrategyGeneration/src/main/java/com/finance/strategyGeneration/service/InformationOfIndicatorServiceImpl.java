package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.repository.InformationOfIndicatorRepository;
import com.finance.strategyGeneration.service.mapper.InformationOfIndicatorMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfIndicatorServiceImpl implements InformationOfIndicatorService {

    InformationOfIndicatorRepository repository;
    InformationOfIndicatorMapper mapper;

    // TODO ДОБАВИТЬ КЭШИРОВАНИЕ
    @Override
    public Indicator findById(Long id) {
        return repository.findById(id).map(mapper::mapTo).orElseThrow(() -> new RuntimeException("Не найдена сущность Indicator с Id="+id));
    }

    @Override
    public Indicator save(Indicator indicator) {

        InformationOfIndicator entity = mapper.mapTo(indicator);
        return repository.findByHashCode(indicator.hashCode()).map(mapper::mapTo)
                .orElse(mapper.mapTo(repository.save(entity)));
    }
}
