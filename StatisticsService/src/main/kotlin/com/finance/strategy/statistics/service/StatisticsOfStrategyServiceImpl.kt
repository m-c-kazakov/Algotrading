package com.finance.strategy.statistics.service

import com.finance.strategy.statistics.model.StatisticsOfStrategy
import com.finance.strategy.statistics.repository.StatisticsOfStrategyRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class StatisticsOfStrategyServiceImpl(
    private val repository: StatisticsOfStrategyRepository,
) : StatisticsOfStrategyService {

    @Transactional(readOnly = true)
    override fun findAll(): List<StatisticsOfStrategy> {
        return repository
            .findAll()
            .toList()
    }

    @Transactional(readOnly = true)
    override fun findAll(offset: Int, limit: Int): List<StatisticsOfStrategy> {
        val pageRequest = PageRequest.of(offset, limit, Sort.Direction.DESC, "id")
        return repository.findAll(pageRequest).content
    }
}