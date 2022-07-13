package com.finance.strategyGeneration.service.broker;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface DataProducer {
    void dataHandler(SpecificationOfStrategy specificationOfStrategy);

    void dataHandler(List<SpecificationOfStrategy> value);
}
