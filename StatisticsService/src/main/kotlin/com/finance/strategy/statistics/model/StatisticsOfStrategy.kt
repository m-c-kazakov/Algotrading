package com.finance.strategy.statistics.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("statistics_of_strategy")
data class StatisticsOfStrategy(
    @Id
    val id: Long = 0,
    val specificationOfStrategyId: Long = 0,
    val score: Long = 0,
    val valueOfAcceptableRisk: Int = 0,
    val maximumPercentDrawdownFromStartScore: Long = 0,
    val averagePercentDrawdownOfScore: Long = 0,
    val maximumValueFromScore: Long = 0,
    val numberOfWinningDeals: Int = 0,
    val numberOfLosingDeals: Int = 0
)
