package com.finance.strategyGeneration.controller;

import com.finance.strategyGeneration.service.StatisticsDataOfStrategyService;
import com.finance.utils.GeneticAlgorithmApi;
import com.finance.utils.StatisticsDataOfStrategyDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneticAlgorithmController implements GeneticAlgorithmApi {

    StatisticsDataOfStrategyService statisticsDataOfStrategyService;

    @Override
    @PostMapping("/strategyGeneration/statisticsDataOfStrategy")
    public void saveStatisticsDataOfStrategy(@RequestBody StatisticsDataOfStrategyDto statisticsDataOfStrategyDto) {

        statisticsDataOfStrategyService.save(statisticsDataOfStrategyDto);
    }
}
