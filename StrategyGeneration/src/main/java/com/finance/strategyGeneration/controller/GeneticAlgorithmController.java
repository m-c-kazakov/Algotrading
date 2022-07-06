package com.finance.strategyGeneration.controller;

import com.finance.createIndicatorData.GeneticAlgorithmApi;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyGeneration.service.StatisticsDataOfStrategyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneticAlgorithmController implements GeneticAlgorithmApi {

    StatisticsDataOfStrategyService statisticsDataOfStrategyService;

    @PostMapping("/strategyGeneration/statisticsDataOfStrategy")
    public void saveStatisticsDataOfStrategy(@RequestBody StatisticsDataOfStrategy statisticsDataOfStrategy) {

        statisticsDataOfStrategyService.save(statisticsDataOfStrategy);
    }
}
