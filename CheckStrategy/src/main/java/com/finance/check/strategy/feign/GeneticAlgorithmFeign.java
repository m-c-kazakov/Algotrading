package com.finance.check.strategy.feign;

import com.finance.utils.GeneticAlgorithmApi;
import com.finance.utils.StatisticsDataOfStrategyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "geneticAlgorithmFeignName", url = "${app.geneticAlgorithm.url}")
public interface GeneticAlgorithmFeign extends GeneticAlgorithmApi {

    @Override
    @PostMapping("/strategyGeneration/statisticsDataOfStrategy")
    void saveStatisticsDataOfStrategy(@RequestBody StatisticsDataOfStrategyDto statisticsDataOfStrategyDto);
}
