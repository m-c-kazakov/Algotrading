package com.finance.strategyGeneration.random.indicator;

import java.util.Map;
import java.util.function.Supplier;


/**
 * Бин должен называться по имени в IndicatorType
 */
public interface RandomIndicatorParametersGenerator {

    Map<String, String> get();

    Map<String, Supplier<String>> getParametersSupplierMap();
}
