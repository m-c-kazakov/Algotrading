package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.RandomPopulationCreationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StrategyInformationRepositoryTest extends IntegrationTestBased {

//    @Autowired
    StrategyInformationRepository repository;

    @Autowired
    RandomPopulationCreationManager randomPopulationCreationManager;


    @BeforeEach
    public void beforeEach() {
//
//        DescriptionOfStrategy descriptionOfStrategy = randomPopulationCreationManager.execute();
//
//        SpecificationOfStrategy specificationOfStrategy = StrategyInformationMapper.INSTANCE.mapTo(
//                descriptionOfStrategy);
//
//
//        StrategyInformation strategyInformation1 = StrategyInformation.builder()
//                .specificationOfStrategy(specificationOfStrategy)
//                .statisticsOfStrategy(StatisticsOfStrategy.builder().score(100).build())
//                .build();
//
//        StrategyInformation strategyInformation2 = StrategyInformation.builder()
//                .specificationOfStrategy(specificationOfStrategy)
//                .statisticsOfStrategy(StatisticsOfStrategy.builder().score(99999999).build())
//                .build();
//
//        StrategyInformation strategyInformation3 = StrategyInformation.builder()
//                .specificationOfStrategy(specificationOfStrategy)
//                .build();
//
//
//        repository.save(strategyInformation2);
//        repository.save(strategyInformation3);
//        repository.save(strategyInformation1);
    }

    @Test
    public void save() {
//        assertThat(repository.findAll()).hasSize(3);
    }

    @Test
    void findAll() {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("statisticsOfStrategy.score"))
//        Sort by = Sort.by(Sort.Direction.DESC, "statisticsOfStrategy.score").ascending();
//        repository.findAll(by);
    }
}