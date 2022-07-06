package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.model.StrategyInformation;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

// TODO
public interface StrategyInformationRepository extends MongoRepository<StrategyInformation, String> {


    List<StrategyInformation> findAll(Sort sort);


}
