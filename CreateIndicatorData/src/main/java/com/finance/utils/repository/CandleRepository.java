package com.finance.utils.repository;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.utils.model.Candle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandleRepository extends CrudRepository<Candle, Long> {


    List<Candle> findAllByTickerAndPer(CurrencyPair ticker, int per);
}
