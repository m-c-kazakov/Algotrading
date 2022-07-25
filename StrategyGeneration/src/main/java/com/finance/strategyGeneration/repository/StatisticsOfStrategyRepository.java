package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticsOfStrategyRepository extends CrudRepository<StatisticsOfStrategy, Long> {

    @Query(value = "SELECT stat.specification_of_strategy_id FROM statistics_of_strategy stat ORDER BY stat.score DESC LIMIT :count")
    List<Long> findTheBestScore(@Param("count") int count);

    @Query(value = "SELECT stat.specification_of_strategy_id FROM statistics_of_strategy stat ORDER BY stat.maximum_value_from_score DESC LIMIT :count")
    List<Long> findMaximumScoreStrategyId(@Param("count") int count);

}
