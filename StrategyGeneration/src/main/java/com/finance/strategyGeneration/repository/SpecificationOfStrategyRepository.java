package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpecificationOfStrategyRepository extends CrudRepository<SpecificationOfStrategy, Long> {

    List<SpecificationOfStrategy> findAll();

    Optional<SpecificationOfStrategy> findByHashCode(long hashCode);

    @Modifying
    @Query("UPDATE specification_of_strategy SET statistics_of_strategy_id= :statisticsOfStrategyId WHERE id= :id")
    void updateStatisticsOfStrategyId(@Param("id") Long id, @Param("statisticsOfStrategyId") Long statisticsOfStrategyId);

    Boolean existsByHashCode(String hashCode);

    @Query("SELECT * FROM specification_of_strategy spec WHERE spec.id IN (SELECT stat.specification_of_strategy_id FROM statistics_of_strategy stat ORDER BY score DESC LIMIT :count)")
    List<SpecificationOfStrategy> findTheBestStrategy(@Param("count") int count);

    @Query("SELECT * FROM specification_of_strategy spec WHERE spec.statistics_of_strategy_id IS NULL ")
    Integer findTheNumberOfUntestedStrategies();
}
