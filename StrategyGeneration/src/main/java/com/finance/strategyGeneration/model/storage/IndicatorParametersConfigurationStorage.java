package com.finance.strategyGeneration.model.storage;

import lombok.Value;

import java.util.Map;

@Value
public class IndicatorParametersConfigurationStorage {

    Map<String, String> parameters;
}
