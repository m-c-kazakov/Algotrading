package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpecificationOfStrategyRepository extends CrudRepository<SpecificationOfStrategy, Long> {


    @Modifying
    @Query("UPDATE specification_of_strategy SET statistics_of_strategy_id= :statisticsOfStrategyId WHERE id= :id")
    boolean updateStatisticsOfStrategyId(@Param("id") Long id, @Param("statisticsOfStrategyId") Long statisticsOfStrategyId);

    Boolean existsByHashCode(Integer hashCode);
}
