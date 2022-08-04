package com.finance.strategy.statistics.repository

import com.finance.strategy.statistics.model.StatisticsOfStrategy
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface StatisticsOfStrategyRepository : CrudRepository<StatisticsOfStrategy, Long> {

    fun findAll(pageable: Pageable): Page<StatisticsOfStrategy>
}