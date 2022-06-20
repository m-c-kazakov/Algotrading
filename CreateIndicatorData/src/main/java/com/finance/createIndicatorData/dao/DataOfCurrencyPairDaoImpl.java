package com.finance.createIndicatorData.dao;

import com.finance.createIndicatorData.model.DataOfCurrencyPair;
import org.springframework.stereotype.Component;

@Component
public class DataOfCurrencyPairDaoImpl implements DataOfCurrencyPairDao {


    @Override
    public DataOfCurrencyPair getDataOfCurrencyPair(DataOfCurrencyPair dataOfCurrencyPair) {
        return DataOfCurrencyPair.builder()
                .build();
    }
}
