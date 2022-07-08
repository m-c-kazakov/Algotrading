package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.model.InformationOfCandles;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InformationOfCandlesRepository extends CrudRepository<InformationOfCandles, Long> {

    Boolean existsByHashCode(long hashCode);

    Optional<InformationOfCandles> findByHashCode(long hashCode);
}
