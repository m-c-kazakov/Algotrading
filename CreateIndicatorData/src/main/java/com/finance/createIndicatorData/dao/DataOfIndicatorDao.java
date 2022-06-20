package com.finance.createIndicatorData.dao;

import com.finance.createIndicatorData.model.DataOfIndicator;

import java.util.Optional;

public interface DataOfIndicatorDao {
    Optional<DataOfIndicator> getDataOfIndicator(String candlesInformationToString);

}
