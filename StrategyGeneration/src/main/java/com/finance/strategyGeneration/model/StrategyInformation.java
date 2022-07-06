package com.finance.strategyGeneration.model;


import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public class StrategyInformation {

    @Id
    String id;

    SpecificationOfStrategy specificationOfStrategy;

    StatisticsOfStrategy statisticsOfStrategy;
}
