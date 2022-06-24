package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DataOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PopulationDaoImpl implements PopulationDao {
    @Override
    public List<DataOfStrategy> findTheBestIndividual(int numberOfIndividuals) {
        // TODO Доработать при подключении БД
        return List.of();
    }
}
