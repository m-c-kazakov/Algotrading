package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InformationOfIndicatorRepository extends CrudRepository<InformationOfIndicator, Long> {

    Boolean existsByHashCode(String hashCode);

    Optional<InformationOfIndicator> findByHashCode(String hashCode);

    List<InformationOfIndicator> findAllByIdIn(List<Long> ids);

}
