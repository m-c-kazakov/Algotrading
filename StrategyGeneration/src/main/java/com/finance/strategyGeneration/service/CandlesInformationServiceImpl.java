package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyGeneration.repository.InformationOfCandlesRepository;
import com.finance.strategyGeneration.service.mapper.InformationOfCandlesMapper;
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
    public CandlesInformation save(CandlesInformation candlesInformation) {

        return repository.findByHashCode(candlesInformation.hashCode())
                .map(mapper::mapTo)
                .orElseGet(() -> {
                    log.warn("CandlesInformation={}, hashCode={}", candlesInformation, candlesInformation.hashCode());
                    CandlesInformation candlesInformation1 = mapper.mapTo(
                            repository.save(mapper.mapTo(candlesInformation)));
                    return candlesInformation1;
                });
    }
}
