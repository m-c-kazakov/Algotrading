package com.finance.strategy.statistics.repository

import com.finance.strategy.statistics.model.StatisticsOfStrategy
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface StatisticsOfStrategyRepository : CrudRepository<StatisticsOfStrategy, Long> {

    fun findAll(pageable: Pageable): Page<StatisticsOfStrategy>

    @Query(value = "SELECT * FROM statistics_of_strategy stat WHERE stat.maximum_value_from_score > 0 ORDER BY stat.score DESC LIMIT :count")
    fun findTheBestScore(@Param("count") count: Int): List<StatisticsOfStrategy>
}