package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface ManagerOfSendingForVerification {

    void execute(SpecificationOfStrategy specificationOfStrategy);

    void execute(List<SpecificationOfStrategy> specificationOfStrategies);
}
