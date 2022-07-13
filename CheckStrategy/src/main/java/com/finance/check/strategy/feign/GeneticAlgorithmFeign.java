package com.finance.check.strategy.feign;

import com.finance.createIndicatorData.GeneticAlgorithmApi;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GeneticAlgorithmFeign")
public interface GeneticAlgorithmFeign extends GeneticAlgorithmApi {

    @PostMapping("/strategyGeneration/statisticsDataOfStrategy")
    void saveStatisticsDataOfStrategy(StatisticsDataOfStrategy statisticsDataOfStrategy);
}
