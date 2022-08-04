package com.finance.strategy.statistics.service

import com.finance.strategy.statistics.model.StatisticsOfStrategy

interface StatisticsOfStrategyService {

    fun findAll(): List<StatisticsOfStrategy>

    fun findAll(offset: Int, limit: Int): List<StatisticsOfStrategy>
}