package com.finance.check.strategy.feign;

import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.utils.GeneticAlgorithmApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GeneticAlgorithmFeign", url = "http://localhost:8082/")
public interface GeneticAlgorithmFeign extends GeneticAlgorithmApi {

    @PostMapping("/strategyGeneration/statisticsDataOfStrategy")
    void saveStatisticsDataOfStrategy(StatisticsDataOfStrategy statisticsDataOfStrategy);
}
