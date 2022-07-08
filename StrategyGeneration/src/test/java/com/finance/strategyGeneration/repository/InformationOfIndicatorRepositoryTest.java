package com.finance.strategyGeneration.repository;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class InformationOfIndicatorRepositoryTest extends IntegrationTestBased {

    @Autowired
    InformationOfIndicatorRepository repository;


    @Test
    void existsByHashCode() {
        assertThat(repository.existsByHashCode(3)).isTrue();
    }

    @Test
    void findByHashCode() {
        assertThat(repository.findByHashCode(3)).isPresent();
    }

    @Test
    void save() {
        Map<String, String> calculate_by = Map.of(
                "CALCULATE_BY", "OPEN",
                "PERIOD", "10");
        repository.save(InformationOfIndicator.builder()
                .indicatorType(IndicatorType.SMA)
                        .informationOfCandlesId(1)
                        .parameters(new IndicatorParametersConfigurationStorage(calculate_by))
                .build());
    }
}