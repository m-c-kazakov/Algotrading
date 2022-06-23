package com.finance.createIndicatorData.dao;

import com.finance.createIndicatorData.model.DataOfIndicator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataOfIndicatorDaoImpl implements DataOfIndicatorDao {
    @Override
    public Optional<DataOfIndicator> getDataOfIndicator(String candlesInformationToString) {
        return Optional.empty();
    }

    @Override
    public void add(DataOfIndicator dataOfIndicator) {
        // TODO
    }
}
