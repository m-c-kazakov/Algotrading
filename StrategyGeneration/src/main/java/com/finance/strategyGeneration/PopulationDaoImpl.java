package com.finance.strategyGeneration;

import com.finance.dataHolder.DataOfStrategy;

import java.util.List;

public class PopulationDaoImpl implements PopulationDao {
    @Override
    public List<DataOfStrategy> findTheBestIndividual(int numberOfIndividuals) {
        // TODO Доработать при подключении БД
        return List.of();
    }
}
