package com.finance.createIndicatorData.repository;

import com.finance.createIndicatorData.model.DataOfIndicator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DataOfIndicatorRepository extends CrudRepository<DataOfIndicator, Long> {

    Optional<DataOfIndicator> getDataOfIndicatorByIndicatorTypeAndAndCurrencyPairAndTimeFrame(String indicatorType,
                                                                                              String currencyPair,
                                                                                              String timeFrame);

}
