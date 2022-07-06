package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DescriptionOfStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class СheckingTheUniquenessOfStrategiesImplTest {

    СheckingTheUniquenessOfStrategiesImpl checkingTheUniquenessOfStrategiesImpl;

    @BeforeAll
    public void beforeEach() {
        checkingTheUniquenessOfStrategiesImpl = new СheckingTheUniquenessOfStrategiesImpl();
    }

    @Test
    void execute() {
        DescriptionOfStrategy el1 = DescriptionOfStrategy.builder().startScore(1000).build();
        DescriptionOfStrategy el2 = DescriptionOfStrategy.builder().startScore(1000).build();
        DescriptionOfStrategy el3 = DescriptionOfStrategy.builder().startScore(9).build();

        assertThat(checkingTheUniquenessOfStrategiesImpl.execute(List.of(el1, el2, el3))).hasSize(2);
    }
}