package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InformationOfIndicatorRepository extends CrudRepository<InformationOfIndicator, Long> {

    Boolean existsByHashCode(long hashCode);
    Optional<InformationOfIndicator> findByHashCode(long hashCode);

    List<InformationOfIndicator> findAllById(List<Long> ids);

}
