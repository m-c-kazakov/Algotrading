package com.finance.strategy.statistics.controller

import com.finance.strategy.statistics.dto.StatisticsOfStrategyDto
import com.finance.strategy.statistics.model.StatisticsOfStrategy
import com.finance.strategy.statistics.service.StatisticsOfStrategyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@CrossOrigin
@RestController
class StatisticsOfStrategyController(
    private val service: StatisticsOfStrategyService

) {
    private val log: Logger = LoggerFactory.getLogger(StatisticsOfStrategyController::class.java)


    @GetMapping("/api/v1/statistics")
    fun findAll(
        @RequestParam(defaultValue = "0") offset: Int,
        @RequestParam(defaultValue = "10") limit: Int,
    ): List<StatisticsOfStrategyDto> {
        log.info(">>GET: /api/v1/statistics: offset=$offset, limit=$limit")

        val statisticsOfStrategyDtos = service
            .findTheBestScore()
            .map { mapTo(it) }

        log.info("response={}", statisticsOfStrategyDtos.get(0))
        return statisticsOfStrategyDtos
    }

    private fun mapTo(statisticsOfStrategy: StatisticsOfStrategy): StatisticsOfStrategyDto {
        return StatisticsOfStrategyDto(
            statisticsOfStrategy.id,
            statisticsOfStrategy.specificationOfStrategyId,
            statisticsOfStrategy.score,
            statisticsOfStrategy.valueOfAcceptableRisk,
            statisticsOfStrategy.maximumPercentDrawdownFromStartScore,
            statisticsOfStrategy.averagePercentDrawdownOfScore,
            statisticsOfStrategy.maximumValueFromScore,
            statisticsOfStrategy.numberOfWinningDeals,
            statisticsOfStrategy.numberOfLosingDeals
        )
    }
}
