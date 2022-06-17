package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DataOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationSelectionImpl implements PopulationSelection {

    СheckingTheUniquenessOfStrategies сheckingTheUniquenessOfStrategies;

    @Override
    public List<DataOfStrategy> execute(List<DataOfStrategy> populationAfterMutation) {

        List<DataOfStrategy> populationAfterCheckingTheUniqueness = сheckingTheUniquenessOfStrategies.execute(
                populationAfterMutation);

        // TODO Проверка на отсутствие уже ранее проверенных стратегий в базе
        return populationAfterCheckingTheUniqueness;
    }
}
