package com.finance.strategyGeneration.service.broker.producer;

import com.finance.strategyGeneration.dto.SpecificationOfStrategyDto;

import java.util.List;

public interface DataProducer {
    void dataHandler(SpecificationOfStrategyDto specificationOfStrategy);

    void dataHandler(List<SpecificationOfStrategyDto> value);
}
