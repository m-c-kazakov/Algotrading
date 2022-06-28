package com.finance.createIndicatorData.repository;

import com.finance.createIndicatorData.model.Candle;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandleRepository extends CrudRepository<Candle, Long> {


    List<Candle> findAllByTickerAndPer(CurrencyPair ticker, int per);
}
