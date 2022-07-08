package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.repository.InformationOfIndicatorRepository;
import com.finance.strategyGeneration.service.mapper.InformationOfIndicatorMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfIndicatorServiceImpl implements InformationOfIndicatorService {

    InformationOfIndicatorRepository repository;
    InformationOfIndicatorMapper mapper;
    CandlesInformationService candlesInformationService;

    // TODO ДОБАВИТЬ КЭШИРОВАНИЕ
    @Override
    public Indicator findById(long id) {
        return repository
                .findById(id)
                .map(mapper::mapTo)
                .map(indicator -> indicator.withCandlesInformation(
                        candlesInformationService.findById(indicator.getCandlesInformationId())))
                .orElseThrow(() -> new RuntimeException("Не найдена сущность Indicator с Id=" + id));
    }

    @Override
    public Indicator save(Indicator indicator) {
        return repository.findByHashCode(indicator.hashCode())
                .map(mapper::mapTo)
                .map(ind -> ind.withCandlesInformation(
                        candlesInformationService.findById(ind.getCandlesInformationId())))
                .orElseGet(() -> mapper.mapTo(repository.save(mapper.mapTo(indicator)))
                        .withCandlesInformation(indicator.getCandlesInformation()));
    }

    @Override
    public Indicator save(IndicatorType indicatorType, CandlesInformation candlesInformation, Map<String, String> parameters) {
        int hashCode = Objects.hash(indicatorType, candlesInformation, parameters);

        InformationOfIndicator informationOfIndicator = repository.findByHashCode(hashCode)
                .orElseGet(() -> repository.save(InformationOfIndicator.builder()
                        .hashCode(hashCode)
                        .indicatorType(indicatorType)
                        .informationOfCandlesId(candlesInformation.getId())
                        .parameters(new IndicatorParametersConfigurationStorage(parameters))
                        .build()));

        return mapper.mapTo(informationOfIndicator).withCandlesInformation(candlesInformationService.findById(
                informationOfIndicator.getInformationOfCandlesId()));
    }
}
