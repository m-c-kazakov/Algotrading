package com.finance.strategyGeneration.model;


import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
public class StrategyInformation {

    @Id
    String id;

    SpecificationOfStrategy specificationOfStrategy;

    StatisticsOfStrategy statisticsOfStrategy;
}
